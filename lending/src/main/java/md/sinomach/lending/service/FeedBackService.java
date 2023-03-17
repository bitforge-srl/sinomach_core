package md.sinomach.lending.service;

import lombok.RequiredArgsConstructor;
import md.sinomach.lending.dao.FeedBackForm;
import md.sinomach.lending.dao.Product;
import md.sinomach.lending.dto.FeedBackFormRequest;
import md.sinomach.lending.dto.FeedBackFormResponse;
import md.sinomach.lending.repository.FeedBackRepository;
import md.sinomach.lending.repository.ProductRepository;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedBackService {
    private final ProductRepository productRepository;
    private final FeedBackRepository feedBackRepository;
    private final EmailService emailService;
    private final TelegramBotService telegramBotService;

    public FeedBackFormResponse processingRequest(FeedBackFormRequest feedBackFormRequest) {
        if ((feedBackFormRequest.getPhone() == null || feedBackFormRequest.getPhone().isEmpty()) &&
                (feedBackFormRequest.getEmail() == null || feedBackFormRequest.getEmail().isEmpty())) {
            return FeedBackFormResponse.failed("Email or Phone is mandatory params");
        }


        feedBackRepository.save(FeedBackForm.builder()
                .comment(feedBackFormRequest.getComment())
                .createdAt(LocalDateTime.now())
                .email(feedBackFormRequest.getEmail())
                .name(feedBackFormRequest.getName())
                .phone(feedBackFormRequest.getPhone())
                .productId(feedBackFormRequest.getProductId())
                .build());


        String messageToSend = createMessageToSend(feedBackFormRequest);

        try {
            emailService.sendEmail(feedBackFormRequest.getEmail(), "New request", messageToSend);
            telegramBotService.sendMessage(messageToSend);
            return FeedBackFormResponse.success();

        } catch (MailException e) {
            System.out.println(messageToSend);
            return FeedBackFormResponse.failed("Message not send");
        }
    }

    public String createMessageToSend(FeedBackFormRequest feedBackFormRequest) {

        final String SEPARATOR = "\n";

        StringBuilder message = new StringBuilder();
        message.append("Feedback:").append(SEPARATOR);

        Optional.ofNullable(feedBackFormRequest.getEmail())
                .ifPresent(email -> message.append("Email: ").append(email).append(SEPARATOR));

        Optional.ofNullable(feedBackFormRequest.getName())
                .ifPresent(name -> message.append("Name: ").append(name).append(SEPARATOR));

        Optional.ofNullable(feedBackFormRequest.getPhone())
                .ifPresent(phone -> message.append("Phone: ").append(phone).append(SEPARATOR));

        Optional.ofNullable(feedBackFormRequest.getComment())
                .ifPresent(comment -> message.append("Phone: ").append(comment).append(SEPARATOR));

        Optional<Long> productId = Optional.ofNullable(feedBackFormRequest.getProductId());
        Optional<Product> productRepositoryById = productRepository.findById(productId.get());
        String nameProduct = productRepositoryById.get().getName();
        productId.ifPresent(aLong -> message.append("Product :").append(nameProduct).append(SEPARATOR));


        return message.toString();
    }


}

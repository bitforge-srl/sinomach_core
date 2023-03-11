package md.sinomach.lending.service;

import lombok.RequiredArgsConstructor;
import md.sinomach.lending.dao.Product;
import md.sinomach.lending.dto.FeedBackFormRequest;
import md.sinomach.lending.dto.FeedBackFormResponse;
import md.sinomach.lending.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedBackService {
    private final ProductRepository productRepository;
    private final PostService postService;

    public FeedBackFormResponse processingRequest(FeedBackFormRequest feedBackFormRequest) {
        String messageToSend = createMessageToSend(feedBackFormRequest);
        if (postService.sendEmail(messageToSend) || postService.sendTelegramMessage(messageToSend)) {
            return FeedBackFormResponse.success();
        }
        return FeedBackFormResponse.failed("Message not send");

    }

    private String createMessageToSend(FeedBackFormRequest feedBackFormRequest) {

        final String SEPARATOR = "\n";

        StringBuilder message = new StringBuilder();
        message.append("Feedback:").append(SEPARATOR);

        Optional.ofNullable(feedBackFormRequest.getEmail())
                .ifPresent(email -> message.append("Email: ").append(email).append(SEPARATOR));


        Optional.ofNullable(feedBackFormRequest.getId())
                .ifPresent(id -> message.append("ID Message: ").append(id).append(SEPARATOR));

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

        Optional.ofNullable(feedBackFormRequest.getTime())
                .ifPresent(date -> message.append("Time: ").append(date));

        return message.toString();
    }


}

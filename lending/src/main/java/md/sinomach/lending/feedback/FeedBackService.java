package md.sinomach.lending.feedback;

import lombok.RequiredArgsConstructor;
import md.sinomach.lending.productPage.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedBackService {
    private final ProductRepository productRepository;
    private final FeedBackRepository feedBackRepository;
    private final List<FeedbackListener> listeners;

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


        Optional.ofNullable(feedBackFormRequest.getProductId())
                .ifPresent(id -> {
                    productRepository.findById(id).ifPresent(
                            product -> feedBackFormRequest.setProductName(product.getName())
                    );
                });

        listeners.forEach(listener -> listener.onNewFeedback(feedBackFormRequest));

        return FeedBackFormResponse.success();
    }


}

package md.sinomach.lending.feedback;

import java.util.Optional;

public class FeedbackUtils {
    public static String createMessageFromFeedback(FeedBackFormRequest feedBackFormRequest) {

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

        Optional<String> productName = Optional.ofNullable(feedBackFormRequest.getProductName());
        productName.ifPresent(aLong -> message.append("Product :").append(productName.get()).append(SEPARATOR));


        return message.toString();
    }
}

package md.sinomach.lending.feedback;

import java.util.Optional;

public class FeedbackUtils {
    public static String createMessageFromFeedback(FeedBackFormRequest feedBackFormRequest) {

        final String SEPARATOR = "\n";

        StringBuilder message = new StringBuilder();
        message.append("Feedback:").append(SEPARATOR);

        Optional.ofNullable(feedBackFormRequest.getEmail())
                .ifPresent(email -> {
                    if (!email.isEmpty()) {
                        message.append("Email: ").append(email).append(SEPARATOR);
                    }
                });

        Optional.ofNullable(feedBackFormRequest.getName())
                .ifPresent(name -> {
                    if (!name.isEmpty()) {
                        message.append("Name: ").append(name).append(SEPARATOR);
                    }
                });


        Optional.ofNullable(feedBackFormRequest.getPhone())
                .ifPresent(phone -> {
                    if (!phone.isEmpty()) {
                        message.append("Phone: ").append(phone).append(SEPARATOR);
                    }
                });

        Optional.ofNullable(feedBackFormRequest.getComment())
                .ifPresent(comment -> {
                    if (!comment.isEmpty()) {
                        message.append("Comment: ").append(comment).append(SEPARATOR);
                    }
                });

        Optional.ofNullable(feedBackFormRequest.getProductName())
                .ifPresent(productName -> {
                    if (!productName.isEmpty()) {
                        message.append("ProductName: ").append(productName).append(SEPARATOR);
                    }
                });

        return message.toString();
    }
}

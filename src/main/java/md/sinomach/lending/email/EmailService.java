package md.sinomach.lending.email;

import lombok.RequiredArgsConstructor;
import md.sinomach.lending.feedback.FeedBackFormRequest;
import md.sinomach.lending.feedback.FeedbackListener;
import md.sinomach.lending.feedback.FeedbackUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailService implements FeedbackListener {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;
    @Value("${md.sinomach.feedback.mail.recipient}")
    private String mailRecipient;

    public boolean sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(to);
            message.setFrom(from);
            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public void onNewFeedback(FeedBackFormRequest feedBackFormRequest) {
        String messageFromFeedback = FeedbackUtils.createMessageFromFeedback(feedBackFormRequest);

        sendEmail(mailRecipient,
                "New request",
                messageFromFeedback);
    }
}

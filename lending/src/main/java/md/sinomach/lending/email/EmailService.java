package md.sinomach.lending.email;

import lombok.RequiredArgsConstructor;
import md.sinomach.lending.feedback.FeedBackFormRequest;
import md.sinomach.lending.feedback.FeedBackService;
import md.sinomach.lending.feedback.FeedbackListener;
import md.sinomach.lending.feedback.FeedbackUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EmailService implements FeedbackListener {
    private final JavaMailSender mailSender;

    public boolean sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setTo(to);
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

        sendEmail(feedBackFormRequest.getEmail(),
                "New request",
                messageFromFeedback);
    }
}

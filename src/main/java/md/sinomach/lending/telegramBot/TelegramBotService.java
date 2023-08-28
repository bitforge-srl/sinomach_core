package md.sinomach.lending.telegramBot;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import md.sinomach.lending.feedback.FeedBackFormRequest;
import md.sinomach.lending.feedback.FeedbackListener;
import md.sinomach.lending.feedback.FeedbackUtils;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TelegramBotService  implements FeedbackListener {
    private final TelegramBot telegramBot;
    private final TelegramBotRepository telegramBotRepository;

    @PostConstruct
    //TODO fix telegram spring boot starter
    public void runAfterObjectCreated() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(telegramBot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public boolean sendMessage(String message) {
        try {
            List<TelegramBotChatId> allChatId = getAllChatId();
            boolean isSuccess = false;
            for (TelegramBotChatId chatId : allChatId) {
                SendMessage snd = new SendMessage();
                snd.setChatId(chatId.getChatId());
                snd.setText(message);

                try {
                    telegramBot.execute(snd);
                    isSuccess = true;
                } catch (TelegramApiException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
            return isSuccess;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private List<TelegramBotChatId> getAllChatId() {
        return telegramBotRepository.findAll();
    }

    @Override
    public void onNewFeedback(FeedBackFormRequest feedBackFormRequest) {
        String messageFromFeedback = FeedbackUtils.createMessageFromFeedback(feedBackFormRequest);
        sendMessage(messageFromFeedback);
    }
}

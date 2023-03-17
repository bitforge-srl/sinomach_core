package md.sinomach.lending.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import md.sinomach.lending.dao.TelegramBotChatId;
import md.sinomach.lending.repository.TelegramBotRepository;
import md.sinomach.lending.telegramBot.TelegramBot;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TelegramBotService {
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

    public void sendMessage(String message) {
        List<TelegramBotChatId> allChatId = getAllChatId();

        for (TelegramBotChatId chatId :
                allChatId) {
            SendMessage snd = new SendMessage();
            snd.setChatId(chatId.getChatId());
            snd.setText(message);

            try {
                telegramBot.execute(snd);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public List<TelegramBotChatId> getAllChatId() {
        return telegramBotRepository.findAll();
    }

}

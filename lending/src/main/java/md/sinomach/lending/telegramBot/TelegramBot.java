package md.sinomach.lending.telegramBot;

import lombok.RequiredArgsConstructor;
import md.sinomach.lending.dao.TelegramBotChatId;
import md.sinomach.lending.repository.TelegramBotRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {
    public final TelegramBotRepository telegramBotRepository;

    @Value("${BOT_NAME}")
    private String BOT_NAME;
    @Value("${BOT_TOKEN}")
    private String BOT_TOKEN;

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage snd = new SendMessage();
        Long chatId = update.getMessage().getChatId();
        snd.setChatId(chatId);

        try {
            telegramBotRepository.save(TelegramBotChatId.builder()
                    .chatId(chatId)
                    .build());
            snd.setText("Вы подписались на рассылку! Ваш chatId:  " + chatId);
        } catch (DataIntegrityViolationException e) {
            snd.setText("Вы уже подписаны на рассылку!");
        }

        try {
            execute(snd);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onRegister() {
        System.out.println("BOT REGISTERED");
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

}

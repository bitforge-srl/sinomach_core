package md.sinomach.lending.telegramBot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TelegramBotRepository extends JpaRepository<TelegramBotChatId, Long> {

    Optional<TelegramBotChatId> findByChatId(long chatID);
}

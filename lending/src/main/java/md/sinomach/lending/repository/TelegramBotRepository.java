package md.sinomach.lending.repository;

import md.sinomach.lending.dao.TelegramBotChatId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelegramBotRepository extends JpaRepository<TelegramBotChatId, Long> {
}

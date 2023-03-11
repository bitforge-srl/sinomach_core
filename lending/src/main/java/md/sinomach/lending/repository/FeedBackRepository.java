package md.sinomach.lending.repository;

import md.sinomach.lending.dao.FeedBackForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedBackRepository extends JpaRepository<FeedBackForm, Long> {
}

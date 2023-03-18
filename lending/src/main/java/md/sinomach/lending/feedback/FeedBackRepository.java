package md.sinomach.lending.feedback;

import md.sinomach.lending.feedback.FeedBackForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedBackRepository extends JpaRepository<FeedBackForm, Long> {
}

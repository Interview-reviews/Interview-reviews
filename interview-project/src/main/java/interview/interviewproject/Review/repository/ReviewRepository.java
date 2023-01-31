package interview.interviewproject.Review.repository;

import interview.interviewproject.Review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}

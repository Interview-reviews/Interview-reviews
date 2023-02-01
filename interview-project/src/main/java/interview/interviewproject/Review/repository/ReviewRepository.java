package interview.interviewproject.Review.repository;

import interview.interviewproject.Review.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByTitleContaining(String keyword);

}

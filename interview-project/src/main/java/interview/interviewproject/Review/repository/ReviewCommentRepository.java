package interview.interviewproject.Review.repository;

import interview.interviewproject.Review.domain.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Long> {

    List<ReviewComment> findByReview(Long id);
}

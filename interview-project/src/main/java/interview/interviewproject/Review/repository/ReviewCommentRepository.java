package interview.interviewproject.Review.repository;

import interview.interviewproject.Review.domain.ReviewComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewCommentRepository extends JpaRepository<ReviewComment, Long> {
}

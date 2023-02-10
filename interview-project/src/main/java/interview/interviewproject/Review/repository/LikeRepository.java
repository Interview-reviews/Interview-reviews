package interview.interviewproject.Review.repository;

import interview.interviewproject.Review.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByReview_IdAndMember_Nickname(Long post_id, String nickname);
    void deleteByReview_IdAndMember_Nickname(Long post_id, String nickname);
}

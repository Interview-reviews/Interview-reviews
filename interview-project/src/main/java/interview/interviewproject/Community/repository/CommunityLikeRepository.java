package interview.interviewproject.Community.repository;

import interview.interviewproject.Community.domain.CommunityLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityLikeRepository extends JpaRepository<CommunityLike , Long> {
}

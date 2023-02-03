package interview.interviewproject.Community.repository;

import interview.interviewproject.Community.domain.CommunityLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityLikeRepository extends JpaRepository<CommunityLike , Long> {

    List<CommunityLike> findAllByCommunityId(Long communityId);

}

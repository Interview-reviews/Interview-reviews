package interview.interviewproject.Community.repository;

import interview.interviewproject.Community.domain.CommunityTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityTagRepository extends JpaRepository<CommunityTag , Long> {

    List<CommunityTag> findAllByCommunityId(Long communityId);
}

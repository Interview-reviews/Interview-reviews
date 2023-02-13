package interview.interviewproject.Community.repository;

import interview.interviewproject.Community.domain.CommunityComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityCommentRepository extends JpaRepository<CommunityComment , Long> {

    List<CommunityComment> findAllByCommunityId(Long communityId);
}

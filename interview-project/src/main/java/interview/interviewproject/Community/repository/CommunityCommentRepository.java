package interview.interviewproject.Community.repository;

import interview.interviewproject.Community.domain.CommunityComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityCommentRepository extends JpaRepository<CommunityComment , Long> {
}

package interview.interviewproject.Community.repository;

import interview.interviewproject.Community.domain.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community , Long> {
}

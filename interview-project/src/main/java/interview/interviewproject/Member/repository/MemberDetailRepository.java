package interview.interviewproject.Member.repository;

import interview.interviewproject.Member.domain.MemberDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDetailRepository extends JpaRepository<MemberDetail, Long> {
}

package interview.interviewproject.Member.repository;

import interview.interviewproject.Member.domain.MemberDetailRequestDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDetailRepository extends JpaRepository<MemberDetailRequestDTO, String> {
}

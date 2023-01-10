package interview.interviewproject.Member.repository;

import interview.interviewproject.Member.domain.MemberRequestDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberRequestDTO, String> {
    boolean existsByNickname(String nickname);
    boolean existsByUserId(String userId);
}

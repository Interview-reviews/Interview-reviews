package interview.interviewproject.Member.repository;

import interview.interviewproject.Member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByNickname(String nickname);

    boolean existsByUsername(String username);

    Member findByEmail(String email);

    Member findByUsername(String username);

    Member findByNickname(String nickname);
}

package interview.interviewproject.Member.repository;

import interview.interviewproject.Member.domain.MemberLanguage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberLanguageRepository extends JpaRepository<MemberLanguage, String> {
}
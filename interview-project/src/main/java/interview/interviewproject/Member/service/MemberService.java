package interview.interviewproject.Member.service;

import interview.interviewproject.Member.domain.Member;
import interview.interviewproject.Member.domain.MemberDetail;
import interview.interviewproject.Member.domain.MemberRequestDTO;
import interview.interviewproject.Member.repository.MemberDetailRepository;
import interview.interviewproject.Member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@RequiredArgsConstructor
public class MemberService {

    @PersistenceContext
    private EntityManager em;

    private MemberRepository memberRepository;

    public boolean nicknameCheck(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    public boolean userIdCheck(String userId) {
        return memberRepository.existsByUserId(userId);
    }

    public void join(MemberRequestDTO memberRequestDTO) {
        Member member = memberRequestDTO.toEntity();
        em.persist(member);
    }
}

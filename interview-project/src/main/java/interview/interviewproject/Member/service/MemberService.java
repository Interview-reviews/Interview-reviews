package interview.interviewproject.Member.service;

import interview.interviewproject.Member.domain.Member;
import interview.interviewproject.Member.domain.MemberDTO;
import interview.interviewproject.Member.domain.MemberDetail;
import interview.interviewproject.Member.repository.MemberDetailRepository;
import interview.interviewproject.Member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public boolean nicknameCheck(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    public boolean userNameCheck(String username) {
        return memberRepository.existsByUsername(username);
    }

    public void createMember(MemberDTO.Request request) {
        Member member = Member.createMember(request);
        memberRepository.save(member);
    }
}

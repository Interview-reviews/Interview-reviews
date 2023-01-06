package interview.interviewproject.Member.service;

import interview.interviewproject.Member.domain.Member;
import interview.interviewproject.Member.domain.MemberDTO;
import interview.interviewproject.Member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public boolean nicknameCheck(String nickname) {
        return memberRepository.nicknameDoubleCheck(nickname);
    }

    public boolean userIdCheck(String userId) {
        return memberRepository.userIdDoubleCheck(userId);
    }

    public void join(Member member) {
        memberRepository.create_member(member);
    }

}

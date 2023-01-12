package interview.interviewproject.Member.service;

import interview.interviewproject.Member.domain.Member;
import interview.interviewproject.Member.domain.MemberDetail;
import interview.interviewproject.Member.domain.MemberRequestDTO;
import interview.interviewproject.Member.repository.MemberDetailRepository;
import interview.interviewproject.Member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public boolean nicknameCheck(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }


    public void join(MemberRequestDTO requestDTO) {
        Member member = MemberRequestDTO.toEntity(requestDTO);
        memberRepository.save(member);
    }
}

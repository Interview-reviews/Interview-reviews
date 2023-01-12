package interview.interviewproject.Member.service;

import interview.interviewproject.Member.domain.MemberDetail;
import interview.interviewproject.Member.domain.MemberDetailRequestDTO;
import interview.interviewproject.Member.repository.MemberDetailRepository;
import interview.interviewproject.Member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDetailService {

    private final MemberDetailRepository memberDetailRepository;


    public void join_detail(MemberDetailRequestDTO memberDetailRequestDTO) {
        MemberDetail memberDetail = MemberDetailRequestDTO.toEntity(memberDetailRequestDTO);
        memberDetailRepository.save(memberDetail);
    }
}

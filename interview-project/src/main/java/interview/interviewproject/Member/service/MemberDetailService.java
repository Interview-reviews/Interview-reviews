package interview.interviewproject.Member.service;

import interview.interviewproject.Member.domain.MemberDetail;
import interview.interviewproject.Member.domain.MemberDetailRequestDTO;
import interview.interviewproject.Member.repository.MemberDetailRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberDetailService {

    private MemberDetailRepository memberDetailRepository;

    public void join_detail(MemberDetailRequestDTO memberDetailRequestDTO) { memberDetailRepository.save(memberDetailRequestDTO); }
}

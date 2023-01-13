package interview.interviewproject.Member.service;

import interview.interviewproject.Member.domain.MemberDetail;
import interview.interviewproject.Member.domain.MemberDetailRequestDTO;
import interview.interviewproject.Member.domain.MemberLanguage;
import interview.interviewproject.Member.repository.MemberDetailRepository;
import interview.interviewproject.Member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import interview.interviewproject.Member.repository.MemberLanguageRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberDetailService {

    private final MemberDetailRepository memberDetailRepository;


    public void join_detail(MemberDetailRequestDTO memberDetailRequestDTO) {
        MemberDetail memberDetail = MemberDetailRequestDTO.toEntity(memberDetailRequestDTO);
        memberDetailRepository.save(memberDetail);
    }
}

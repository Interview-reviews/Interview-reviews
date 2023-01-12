package interview.interviewproject.Member.service;

import interview.interviewproject.Member.domain.MemberDetail;
import interview.interviewproject.Member.domain.MemberDetailRequestDTO;
import interview.interviewproject.Member.domain.MemberLanguage;
import interview.interviewproject.Member.repository.MemberDetailRepository;
import interview.interviewproject.Member.repository.MemberLanguageRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MemberDetailService {

    @PersistenceContext
    private EntityManager em;

    private MemberDetailRepository memberDetailRepository;
    private MemberLanguageRepository memberLanguageRepository;



    public void join_detail(MemberDetailRequestDTO memberDetailRequestDTO) {
        List<List<String>> language = memberDetailRequestDTO.getLanguage();
        for (List<String> s : language) {
            em.persist(s);
        }
        MemberDetail memberDetail = memberDetailRequestDTO.toEntity();
        em.persist(memberDetail);
    }
}

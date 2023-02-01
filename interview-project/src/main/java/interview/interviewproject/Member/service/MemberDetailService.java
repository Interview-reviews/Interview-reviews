package interview.interviewproject.Member.service;

import interview.interviewproject.Member.domain.*;
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
    private final MemberLanguageRepository languageRepository;

    public void join_detail(MemberDetailRequestDTO memberDetailRequestDTO) {
        MemberDetail memberDetail = MemberDetailRequestDTO.toEntity(memberDetailRequestDTO);
        List<MemberLanguageDTO> language = memberDetailRequestDTO.getLanguage();
        Long id = memberDetailRequestDTO.getId();

        for(int i=0; i<language.size(); i++) {
            MemberLanguageDTO memberLanguageDTO = new MemberLanguageDTO(language.get(i).getLanguage(), language.get(i).getLanguageScore());
            MemberLanguage memberLanguage = MemberLanguageDTO.toEntity(id, memberLanguageDTO);
            languageRepository.save(memberLanguage); // 어학점수 저장
        }

        memberDetailRepository.save(memberDetail); // 어학점수 이외의 detail 저장
    }
}

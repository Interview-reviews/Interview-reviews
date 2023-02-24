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

    private final MemberRepository memberRepository;
    private final MemberDetailRepository memberDetailRepository;
    private final MemberLanguageRepository languageRepository;

    public void join_detail(MemberDetailDTO.Request request, String nickname) {
        Member member = memberRepository.findByNickname(nickname);
        MemberDetail memberDetail = MemberDetail.createMemberDetail(request, member);
        List<MemberLanguageDTO.Request> language = request.getLanguage();

        memberDetailRepository.save(memberDetail); // 어학점수 이외의 detail 저장

        // lauguage에 대한 정보저장
        for (MemberLanguageDTO.Request value : language) {
            String language1 = value.getLanguage();
            Long languageScore = value.getLanguageScore();
            MemberLanguage memberLanguage = MemberLanguage.createMemberLanguage(language1, languageScore);
            memberLanguage.setMemberDetail(memberDetail);
            languageRepository.save(memberLanguage);
        }


    }
}

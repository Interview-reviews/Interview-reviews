package interview.interviewproject.Community.service;

import interview.interviewproject.Community.domain.Community;
import interview.interviewproject.Community.domain.CommunityDTO;
import interview.interviewproject.Community.repository.CommunityRepository;
import interview.interviewproject.Member.domain.Member;
import interview.interviewproject.Member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final MemberRepository memberRepository;
    private final CommunityRepository communityRepository;
    public void createCommunity(String username , CommunityDTO.Request request) {

        Member member = memberRepository.findByUsername(username);
        Community community = Community.createCommunity(request, member);

        communityRepository.save(community);
    }
}

package interview.interviewproject.Community.service;

import interview.interviewproject.Community.domain.*;
import interview.interviewproject.Community.repository.CommunityCommentRepository;
import interview.interviewproject.Community.repository.CommunityLikeRepository;
import interview.interviewproject.Community.repository.CommunityRepository;
import interview.interviewproject.Community.repository.CommunityTagRepository;
import interview.interviewproject.Member.domain.Member;
import interview.interviewproject.Member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final MemberRepository memberRepository;
    private final CommunityRepository communityRepository;

    private final CommunityTagRepository communityTagRepository;

    private final CommunityLikeRepository communityLikeRepository;

    private final CommunityCommentRepository communityCommentRepository;
    public void createCommunity(String username , CommunityDTO.Request request) {

        Member member = memberRepository.findByUsername(username);
        Community community = Community.createCommunity(request, member);

        communityRepository.save(community);
    }

    public List<CommunityDTO.Response> getCommunityList() {

        List<CommunityDTO.Response> result = new ArrayList<>();

        List<Community> communities = communityRepository.findAll();

        for (Community community : communities) {

            List<CommunityLike> communityLikeList = communityLikeRepository.findAllByCommunityId(community.getId());
            List<CommunityComment> communityCommentList = communityCommentRepository.findAllByCommunityId(community.getId());

            CommunityDTO.Response response = CommunityDTO.Response.builder()
                    .nickName(community.getMember().getNickname())
                    .title(community.getTitle())
                    .contents(community.getContents())
                    .category(community.getCategory())
                    .views(community.getViews())
                    .likes(communityLikeList.size())
                    .comments(communityCommentList.size())
                    .createdAt(LocalDate.from(community.getCreatedAt()))
                    .communityTagList(new ArrayList<>())
                    .build();


            List<CommunityTag> communityTagList = communityTagRepository.findAllByCommunityId(community.getId());
            for (CommunityTag communityTag : communityTagList) {

                response.getCommunityTagList().add(CommunityTagDTO.builder()
                        .tagName(communityTag.getTagName()).build());
            }

            result.add(response);

        }

        return result;
    }

}

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

import javax.transaction.Transactional;
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

    @Transactional
    public CommunityDTO.Response getCommunity(String nickname, Long communityId) {
        Community community = communityRepository.findById(communityId).get();

        if(community == null) {
            throw new IllegalArgumentException("등록된 커뮤니티가 없습니다.");
        }

        community.addViews();

        List<CommunityLike> communityLikeList = communityLikeRepository.findAllByCommunityId(community.getId());
        List<CommunityComment> communityCommentList = communityCommentRepository.findAllByCommunityId(community.getId());

        CommunityDTO.Response response = CommunityDTO.Response.builder()
                .id(community.getId())
                .nickName(community.getMember().getNickname())
                .title(community.getTitle())
                .contents(community.getContents())
                .category(community.getCategory())
                .views(community.getViews())
                .likes(communityLikeList.size())
                .isLiked(community.isLiked())
                .comments(communityCommentList.size())
                .createdAt(LocalDate.from(community.getCreatedAt()))
                .communityTagList(new ArrayList<>())
                .communityCommentList(new ArrayList<>())
                .build();


        List<CommunityTag> communityTagList = communityTagRepository.findAllByCommunityId(community.getId());
        for (CommunityTag communityTag : communityTagList) {

            response.getCommunityTagList().add(CommunityTagDTO.builder()
                    .tagName(communityTag.getTagName()).build());
        }

        for (CommunityComment comment : communityCommentList) {
            CommunityCommentDTO build = CommunityCommentDTO.builder()
                    .content(comment.getContents())
                    .nickname(comment.getNickName())
                    .createAt(LocalDate.from(comment.getCreatedAt()))
                    .build();

            if(comment.getNickName().equals(nickname)){
                build.setOwner(true);
            }else {
                build.setOwner(false);
            }

            response.getCommunityCommentList().add(build);
        }

        if(community.getMember().getNickname().equals(nickname)){
            response.setOwner(true);
        }else {
            response.setOwner(false);
        }

        return response;
    }

    public List<CommunityDTO.Response> getCommunityList(String nickname) {

        List<CommunityDTO.Response> result = new ArrayList<>();

        List<Community> communities = communityRepository.findAll();

        for (Community community : communities) {

            List<CommunityLike> communityLikeList = communityLikeRepository.findAllByCommunityId(community.getId());
            List<CommunityComment> communityCommentList = communityCommentRepository.findAllByCommunityId(community.getId());

            CommunityDTO.Response response = CommunityDTO.Response.builder()
                    .id(community.getId())
                    .nickName(community.getMember().getNickname())
                    .title(community.getTitle())
                    .contents(community.getContents())
                    .category(community.getCategory())
                    .views(community.getViews())
                    .likes(communityLikeList.size())
                    .isLiked(community.isLiked())
                    .comments(communityCommentList.size())
                    .createdAt(LocalDate.from(community.getCreatedAt()))
                    .communityTagList(new ArrayList<>())
                    .communityCommentList(new ArrayList<>())
                    .build();


            List<CommunityTag> communityTagList = communityTagRepository.findAllByCommunityId(community.getId());
            for (CommunityTag communityTag : communityTagList) {

                response.getCommunityTagList().add(CommunityTagDTO.builder()
                        .tagName(communityTag.getTagName()).build());
            }

            for (CommunityComment comment : communityCommentList) {
                CommunityCommentDTO build = CommunityCommentDTO.builder()
                        .content(comment.getContents())
                        .nickname(comment.getNickName())
                        .createAt(LocalDate.from(comment.getCreatedAt()))
                        .build();

                if(comment.getNickName().equals(nickname)){
                    build.setOwner(true);
                }else {
                    build.setOwner(false);
                }

                response.getCommunityCommentList().add(build);
            }

            if(community.getMember().getNickname().equals(nickname)){
                response.setOwner(true);
            }else {
                response.setOwner(false);
            }

            result.add(response);

        }

        return result;
    }

    @Transactional
    public void createLike(Long memberId, Long communityId) {

        Community community = communityRepository.findById(communityId).get();
        Member member = memberRepository.findById(memberId).get();

        if(community == null) {
            throw new IllegalArgumentException("등록된 커뮤니티가 없습니다.");
        }

        CommunityLike communityLike = communityLikeRepository.findByCommunityIdAndMemberId(communityId, memberId);

        if(communityLike != null) {
            communityLikeRepository.deleteById(communityLike.getId());
            community.setLiked(false);
        }else {
            CommunityLike build = CommunityLike.builder()
                    .member(member)
                    .community(community)
                    .build();

            communityLikeRepository.save(build);
            community.setLiked(true);
        }

    }

    public void createComment(String nickname, Long communityId , String content) {
        Community community = communityRepository.findById(communityId).get();

        if(community == null) {
            throw new IllegalArgumentException("등록된 커뮤니티가 없습니다.");
        }

        CommunityComment comment = CommunityComment.builder()
                .community(community)
                .nickName(nickname)
                .contents(content)
                .build();

        communityCommentRepository.save(comment);
    }
}

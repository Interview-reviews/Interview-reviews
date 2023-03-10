package interview.interviewproject.Community.controller;

import interview.interviewproject.Common.auth.annotation.LoginMember;
import interview.interviewproject.Community.domain.CommunityDTO;
import interview.interviewproject.Community.service.CommunityService;
import interview.interviewproject.Member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/community")
public class CommunityController {

    private final CommunityService communityService;
    @PostMapping()
    public void createCommunity(@LoginMember Member member , @RequestBody CommunityDTO.Request request) {
        communityService.createCommunity(member.getUsername() , request);
    }

    @GetMapping()
    public List<CommunityDTO.Response> getCommunityList(@LoginMember Member member) {
        return communityService.getCommunityList(member.getNickname());
    }

    @GetMapping("/{communityId}")
    public CommunityDTO.Response getCommunity(@LoginMember Member member , @PathVariable Long communityId) {
        return communityService.getCommunity(member.getNickname() , communityId);
    }


    @PostMapping("/like/{communityId}")
    public void createLike(@LoginMember Member member , @PathVariable Long communityId) {
        communityService.createLike(member.getId() , communityId);
    }

    @PostMapping("/comment/{communityId}")
    public void createComment(@LoginMember Member member , @PathVariable Long communityId , @RequestBody String content) {
        communityService.createComment(member.getNickname() , communityId , content);
    }

    @PostMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public void test(@LoginMember Member member , @RequestBody CommunityDTO.Request request){
        System.out.println("request = " + request);
    }
}

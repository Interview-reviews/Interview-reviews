package interview.interviewproject.Community.controller;

import interview.interviewproject.Common.annotation.LoginMember;
import interview.interviewproject.Community.domain.CommunityDTO;
import interview.interviewproject.Community.domain.CommunityTag;
import interview.interviewproject.Community.domain.CommunityTagDTO;
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
        return communityService.getCommunityList();
    }

    @PostMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    public void test(@LoginMember Member member , @RequestBody CommunityDTO.Request request){
        System.out.println("request = " + request);
    }
}

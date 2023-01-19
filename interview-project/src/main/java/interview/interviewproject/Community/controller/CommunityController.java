package interview.interviewproject.Community.controller;

import interview.interviewproject.Common.annotation.LoginMember;
import interview.interviewproject.Community.domain.CommunityDTO;
import interview.interviewproject.Community.domain.CommunityTag;
import interview.interviewproject.Community.service.CommunityService;
import interview.interviewproject.Member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/community")
public class CommunityController {

    private final CommunityService communityService;
    @PostMapping()
    public void createCommunity(@LoginMember Member member , @RequestBody CommunityDTO.Request request) {
        communityService.createCommunity(member.getUsername() , request);
    }
}

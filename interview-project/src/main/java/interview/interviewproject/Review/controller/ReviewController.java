package interview.interviewproject.Review.controller;

import interview.interviewproject.Common.annotation.LoginMember;
import interview.interviewproject.Member.domain.Member;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

    @GetMapping("/test")
    public String test(@LoginMember Member member){
        System.out.println("member.getUsername() = " + member.getUsername());
        System.out.println("member.getEmail() = " + member.getEmail());
        return "test";
    }
}

package interview.interviewproject.Member.controller;

import interview.interviewproject.Member.domain.MemberDetail;
import interview.interviewproject.Member.domain.MemberDetailRequestDTO;
import interview.interviewproject.Member.domain.MemberRequestDTO;
import interview.interviewproject.Member.service.MemberDetailService;
import interview.interviewproject.Member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;
    private final MemberDetailService memberDetailService;

    //닉네임 중복확인
    @GetMapping(value = "/check-nickname")
    public boolean checkNickname(@RequestParam String nickname) {
        //중복시에 false 로 반환
        return !memberService.nicknameCheck(nickname);
    }

    //아이디 중복확인
    @GetMapping(value = "/check-userid")
    public boolean checkUserId(@RequestParam String userId) {
        //중복시에 false로 반환
        return !memberService.userIdCheck(userId);
    }

    //회원가입-회원정보
    @PostMapping(value = "")
    public void create_member(@RequestBody MemberRequestDTO memberRequestDTO) {
        memberService.join(memberRequestDTO);
    }

    //회원가입-스펙정보
    @PostMapping(value = "/detail")
    public void create_detailMember(@RequestBody MemberDetailRequestDTO memberDetailRequestDTO) {
        memberDetailService.join_detail(memberDetailRequestDTO);
    }
}

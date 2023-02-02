package interview.interviewproject.Member.controller;

import interview.interviewproject.Member.domain.*;
import interview.interviewproject.Member.service.MemberDetailService;
import interview.interviewproject.Member.service.MemberService;
import interview.interviewproject.Member.service.emailservice.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;
    private final MemberDetailService memberDetailService;
    private final EmailService emailService;

    //닉네임 중복확인
    @GetMapping(value = "/check-nickname")
    public boolean checkNickname(@RequestParam String nickname) {
        //중복시에 false 로 반환
        return !memberService.nicknameCheck(nickname);
    }

    // 아이디 중복확인
    @GetMapping(value = "/check-username")
    public boolean checkUserId(@RequestParam String username) {
        return memberService.userNameCheck(username);
    }

    //회원가입-회원정보
    @PostMapping(value = "/join")
    public void create_member(@RequestBody MemberRequestDTO memberRequestDTO) {
        memberService.join(memberRequestDTO);
    }

    //회원가입-스펙정보
    @PostMapping(value = "/detail")
    public void create_detailMember(@RequestBody MemberDetailRequestDTO memberDetailRequestDTO) {
        memberDetailService.join_detail(memberDetailRequestDTO);
    }

    // 이메일 인증
    @PostMapping(value = "/emailConfirm")
    public String emailConfirm(@RequestParam String email) throws Exception {

        return emailService.memberJoin(email);
    }

    // 아이디 찾기
    @GetMapping(value = "find-username")
    public MemberFindUsernameDTO findUserName(@RequestParam String email) throws  Exception {
        return emailService.findUserName(email);
    }

    // 비밀번호 찾기
    @GetMapping(value = "find-password")
    public MemberFindPw findPw(@RequestParam String email) throws Exception {
        return emailService.findPw(email);
    }
}

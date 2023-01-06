package interview.interviewproject.Member.controller;

import interview.interviewproject.Member.domain.Member;
import interview.interviewproject.Member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "/member")
    public ResponseEntity<String> create_member(@RequestBody Member member) {
        memberService.join(member);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @GetMapping(value = "/member/check-nickname")
    public ResponseEntity<String> checkNickname(@RequestParam String nickname) {
        if (memberService.nicknameCheck(nickname)) {
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("FAILED", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/member/check-userid")
    public ResponseEntity<String> checkUserId(@RequestParam String userId) {
        if (memberService.nicknameCheck(userId)) {
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("FAILED", HttpStatus.BAD_REQUEST);
        }

    }
}

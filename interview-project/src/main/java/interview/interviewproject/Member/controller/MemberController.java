package interview.interviewproject.Member.controller;

import interview.interviewproject.Member.domain.Member;
import interview.interviewproject.Member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping()
    public ResponseEntity<String> create_member(@RequestBody Member member) {
        memberService.join(member);
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }

    @GetMapping(value = "/check-nickname")
    public ResponseEntity<String> checkNickname(@RequestParam String nickname) {
        if (memberService.nicknameCheck(nickname)) {
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("FAILED", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(value = "/check-userid")
    public ResponseEntity<String> checkUserId(@RequestParam String userId) {
        if (memberService.nicknameCheck(userId)) {
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("FAILED", HttpStatus.BAD_REQUEST);
        }

    }
}

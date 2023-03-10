package interview.interviewproject.Member.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberFindPwDTO { // DTO로 변경예정

    private String authPw;
    private String password;

    @Builder
    public MemberFindPwDTO(String password, String authPw) {
        this.authPw = authPw;
        this.password = password;
    }
}

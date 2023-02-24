package interview.interviewproject.Member.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberFindPwDTO {

    private String authPw;
    private String password;

    @Builder
    public MemberFindPwDTO(String password, String authPw) {
        this.authPw = authPw;
        this.password = password;
    }
}

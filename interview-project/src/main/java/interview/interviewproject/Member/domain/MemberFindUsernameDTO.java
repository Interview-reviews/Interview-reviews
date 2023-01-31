package interview.interviewproject.Member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberFindUsernameDTO {

    private String authPw;
    private String username;

    @Builder
    public MemberFindUsernameDTO(String username, String authPw) {
        this.authPw = authPw;
        this.username = username;
    }
}

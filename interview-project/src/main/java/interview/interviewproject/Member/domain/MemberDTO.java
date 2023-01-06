package interview.interviewproject.Member.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {

    private String birthdate;
    private String email;
    private String userId;
    private String nickname;
    private String password;
    private GenderType gender;

}

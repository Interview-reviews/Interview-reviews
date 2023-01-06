package interview.interviewproject.Member.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemberDTO {

    @Getter
    @Builder
    public static class Request {

        private String nickname;
        private String memberName;
        private String userId;
        private String password;
        private LocalDate birthDate;
        private String email;
        private GenderType gender;
        private String phoneNum;

    }

    public static class Response{

    }

}

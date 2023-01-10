package interview.interviewproject.Member.domain;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemberRequestDTO {

        private String nickname;
        private String username;
        private String phoneNumber;
        private String userId;
        private String password;
        private String email;
        private LocalDate birthDate;
        private GenderType gender;
}

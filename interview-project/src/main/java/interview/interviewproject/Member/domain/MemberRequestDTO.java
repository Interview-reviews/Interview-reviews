package interview.interviewproject.Member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class MemberRequestDTO {

        private String nickname;
        private String username;
        private Long phoneNumber;
        private String userId;
        private String password;
        private String email;
        private LocalDate birthDate;
        private GenderType gender;

        public  Member toEntity() {
                return Member.builder()
                        .nickname(nickname)
                        .username(username)
                        .phoneNumber(phoneNumber)
                        .userId(userId)
                        .password(password)
                        .email(email)
                        .birthDate(birthDate)
                        .gender(gender)
                        .build();

        }
}

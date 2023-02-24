package interview.interviewproject.Member.domain;

import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

public class MemberDTO {

        @Builder
        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Request{

                private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

                private String nickname;
                private String username;
                private String phoneNumber;
                private String password;
                private String email;
                private LocalDate birthDate;
                private GenderType gender;

                public String passwordEncode(String password) {
                        System.out.println("password = " + password);
                        return bCryptPasswordEncoder.encode(password);
                }
        }


}

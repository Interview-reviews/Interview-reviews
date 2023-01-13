package interview.interviewproject.Member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDTO {

        private  BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        private String nickname;
        private String username;
        private Long phoneNumber;
        private String userId;
        private String password;
        private String email;
        private LocalDate birthDate;
        private GenderType gender;

        public static Member toEntity(MemberRequestDTO requestDTO) {

                return Member.builder()
                        .nickname(requestDTO.getNickname())
                        .username(requestDTO.getUsername())
                        .phoneNumber(requestDTO.getPhoneNumber())
                        .password(requestDTO.passwordEncode(requestDTO.getPassword()))
                        .email(requestDTO.getEmail())
                        .birthDate(requestDTO.getBirthDate())
                        .gender(requestDTO.getGender())
                        .role("USER")
                        .build();
        }

        public String passwordEncode(String password) {
                System.out.println("password = " + password);
                return bCryptPasswordEncoder.encode(password);
        }
}

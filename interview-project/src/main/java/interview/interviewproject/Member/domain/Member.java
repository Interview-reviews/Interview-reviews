package interview.interviewproject.Member.domain;

import interview.interviewproject.Common.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "detail_id")
    private MemberDetail memberDetail;

    private String nickname;

    @Column(name = "user_name")
    private String username;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String password;

    private String email;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Enumerated(value = EnumType.STRING)
    private GenderType gender;

    private String role; // USER, ADMIN

    public static Member createMember(MemberDTO.Request request) {
        return Member.builder()
                .nickname(request.getNickname())
                .username(request.getUsername())
                .phoneNumber(request.getPhoneNumber())
                .password(request.passwordEncode(request.getPassword()))
                .email(request.getEmail())
                .birthDate(request.getBirthDate())
                .gender(request.getGender())
                .role("USER")
                .build();
    }


}

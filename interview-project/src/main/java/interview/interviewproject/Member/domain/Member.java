package interview.interviewproject.Member.domain;

import interview.interviewproject.Common.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

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

    private String role;

    @Builder
    public Member(String nickname, String username, String phoneNumber, String password, String email,
                  LocalDate birthDate, GenderType gender, String role) {
        this.nickname = nickname;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
        this.role = role;
    }
}

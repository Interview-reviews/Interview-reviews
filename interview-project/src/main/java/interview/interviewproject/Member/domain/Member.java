package interview.interviewproject.Member.domain;

import interview.interviewproject.Common.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private String role;
}
package interview.interviewproject.Member.domain;

import interview.interviewproject.Common.domain.BaseTimeEntity;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    @Column(name = "user_id")
    private String userId;

    private String password;

    private String email;

    @Column(name = "birth_date")
    private String birthDate;

    @Enumerated(value = EnumType.STRING)
    private GenderType gender;

    private String language;

    private String school;

    private Double grades;

    private String major;

    private Boolean intern;

    private String contest;

    @Column(name = "project_num")
    private Integer projectNum;

    private String job;

}

package interview.interviewproject.Member.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class MemberDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "language_id")
    private DetailLanguage detailLanguage;

    private String school;

    private Double grades;

    private String major;

    private int intern;

    private String job; //직무
}

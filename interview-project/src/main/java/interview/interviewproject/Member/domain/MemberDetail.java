package interview.interviewproject.Member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private Long id;

    private String graduate;

    private String school;

    private Double grades;

    private String major;

    private int intern;

    private String job; //직무

    private CareerType careerType; // 신입 or 경력력

    @OneToOne(mappedBy = "memberdetail")
    private Member member;

    @OneToMany
    @JoinColumn(name = "language_id")
    List<MemberLanguage> memberLanguages = new ArrayList<>();

    @Builder
    public MemberDetail(String graduate, String school, Double grades, String major, int intern, String job, CareerType careerType) {
        this.graduate = graduate;
        this.school = school;
        this.grades = grades;
        this.major = major;
        this.intern = intern;
        this.job = job;
        this.careerType = careerType;
    }

}

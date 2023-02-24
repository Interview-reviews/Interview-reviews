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
@Builder
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

    @OneToOne
    @JoinColumn(name = "nickname")
    private Member member;

//    @OneToMany
//    @JoinColumn(name = "language_id")
//    private List<MemberLanguage> memberLanguages;

    public static MemberDetail createMemberDetail(MemberDetailDTO.Request request, Member member) {
        return MemberDetail.builder()
                .graduate(request.getGraduate())
                .school(request.getSchool())
                .grades(request.getGrades())
                .major(request.getMajor())
                .intern(request.getIntern())
                .job(request.getJob())
                .careerType(request.getCareerType())
                .member(member)
                .build();
    }



}

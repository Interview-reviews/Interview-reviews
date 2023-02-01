package interview.interviewproject.Review.domain;


import interview.interviewproject.Common.domain.BaseTimeEntity;
import interview.interviewproject.Member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String company;

    @Column(name = "company_job")
    private String companyJob;

    @Column(name = "support_date")
    private LocalDate supportDate;

    @Column(name = "interview_type")
    private String interviewType;

    @Column(name = "career_type")
    private CareerType careerType;

    @Column(name = "interview_level")
    private String interviewLevel;

    private Boolean passingStatus;

    private String title;

    private String contents;

    private int view_num;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    @OneToMany(mappedBy = "review", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc") // 댓글 정렬
    private List<ReviewComment> comments;
}

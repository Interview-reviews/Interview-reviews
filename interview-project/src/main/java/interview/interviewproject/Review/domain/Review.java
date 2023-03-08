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
    @Column(name = "post_id")
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

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int likes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nickname")
    private Member member;

    @OneToMany(mappedBy = "review", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc") // 댓글 정렬
    private List<ReviewComment> comments;
    
    public static Review createReview(ReviewDTO.Request request) {
        return Review.builder()
                .company(request.getCompany())
                .companyJob(request.getCompanyJob())
                .supportDate(request.getSupportDate())
                .interviewType(request.getInterviewType())
                .careerType(request.getCareerType())
                .interviewLevel(request.getInterviewLevel())
                .passingStatus(request.getPassingStatus())
                .title(request.getTitle())
                .contents(request.getContents())
                .build();
    }

    public void Update(ReviewDTO.Request request) {
        this.company = request.getCompany();
        this.companyJob = request.getCompanyJob();
        this.supportDate = request.getSupportDate();
        this.interviewType = request.getInterviewType();
        this.careerType = request.getCareerType();
        this.interviewLevel = request.getInterviewLevel();
        this.passingStatus = request.getPassingStatus();
        this.title = request.getTitle();
        this.contents = request.getContents();
    }
}

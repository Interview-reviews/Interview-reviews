package interview.interviewproject.Review.domain;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ReviewResponseDTO {
    private String company;
    private String companyJob;
    private LocalDate supportDate;
    private String interviewType;
    private CareerType careerType;
    private String interviewLevel;
    private Boolean passingStatus;
    private String title;
    private String contents;
    private int view_num;
    private Boolean flag; //작성자가 맞는지 확인
    private List<ReviewCommentResponseDTO> commentResponseDTOList;

    @Builder
    public ReviewResponseDTO(Review review) {
        this.company = review.getCompany();
        this.companyJob = review.getCompanyJob();
        this.supportDate = review.getSupportDate();
        this.interviewType = review.getInterviewType();
        this.careerType = review.getCareerType();
        this.interviewLevel = review.getInterviewLevel();
        this.passingStatus = review.getPassingStatus();
        this.title = review.getTitle();
        this.contents = review.getContents();
        this.view_num = review.getView_num();
    }
}

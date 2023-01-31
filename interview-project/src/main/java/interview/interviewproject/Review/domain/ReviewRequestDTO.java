package interview.interviewproject.Review.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class ReviewRequestDTO {

    private String company;
    private String companyJob;
    private LocalDate supportDate;
    private String interviewType;
    private CareerType careerType;
    private String interviewLevel;
    private Boolean passingStatus;
    private String title;
    private String contents;

    public static Review toEntity(ReviewRequestDTO requestDTO) {

        return Review.builder()
                .company(requestDTO.company)
                .companyJob(requestDTO.companyJob)
                .supportDate(requestDTO.supportDate)
                .interviewType(requestDTO.interviewType)
                .careerType(requestDTO.careerType)
                .interviewLevel(requestDTO.interviewLevel)
                .passingStatus(requestDTO.passingStatus)
                .title(requestDTO.title)
                .contents(requestDTO.contents)
                .build();
    }
}

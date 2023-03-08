package interview.interviewproject.Review.domain;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

public class ReviewDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        private String company;
        private String companyJob;
        private LocalDate supportDate;
        private String interviewType;
        private CareerType careerType;
        private String interviewLevel;
        private Boolean passingStatus;
        private String title;
        private String contents;

    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
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
        private int likes_num;

        private Boolean user_flag; //작성자가 맞는지 확인
        private List<ReviewCommentDTO.Response> commentResponseDTOList;
        private Boolean like_flag; // 작성자가 좋아요 여부
    }
}

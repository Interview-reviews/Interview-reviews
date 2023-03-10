package interview.interviewproject.Review.domain;

import interview.interviewproject.Member.domain.Member;
import lombok.*;

public class ReviewCommentDTO {

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        private Long id;
        private String comment;
        private Member member;
        private Review review;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private String nickname;

        private String comments;
    }


}

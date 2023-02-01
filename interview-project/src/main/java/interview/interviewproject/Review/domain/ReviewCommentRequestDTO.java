package interview.interviewproject.Review.domain;

import interview.interviewproject.Member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewCommentRequestDTO {

    private Long id;
    private String comment;
    private String createdDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private String modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    private Member member;
    private Review review;

    public static ReviewComment toEntity(ReviewCommentRequestDTO requestDTO) {
        ReviewComment comment = ReviewComment.builder()
                .id(requestDTO.id)
                .comment(requestDTO.comment)
                .createdDate(requestDTO.createdDate)
                .modifiedDate(requestDTO.modifiedDate)
                .member(requestDTO.member)
                .review(requestDTO.review)
                .build();

        return comment;
    }
}

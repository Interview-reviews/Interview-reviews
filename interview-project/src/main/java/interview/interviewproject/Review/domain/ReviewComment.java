package interview.interviewproject.Review.domain;

import interview.interviewproject.Common.domain.BaseTimeEntity;
import interview.interviewproject.Member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class ReviewComment extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment; // 댓글 내용

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Review review;

    @ManyToOne
    @JoinColumn(name = "nickname")
    private Member member;

    public static ReviewComment createComment(ReviewCommentDTO.Request request, Review review, Member member) {
        return ReviewComment.builder()
                .comment(request.getComment())
                .member(member)
                .review(review)
                .build();
    }


}

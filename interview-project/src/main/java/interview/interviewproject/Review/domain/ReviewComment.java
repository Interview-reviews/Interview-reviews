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
public class ReviewComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String comment; // 댓글 내용

//    @Column(name = "created_date")
//    @CreatedDate
//    private String createdDate;
//
//    @Column(name = "modified_date")
//    @LastModifiedDate
//    private String modifiedDate;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Review review;

    @ManyToOne
    @JoinColumn(name = "nickname")
    private Member member;



}

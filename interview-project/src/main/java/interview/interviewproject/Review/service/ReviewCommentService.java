package interview.interviewproject.Review.service;

import interview.interviewproject.Member.domain.Member;
import interview.interviewproject.Member.repository.MemberRepository;
import interview.interviewproject.Review.domain.Review;
import interview.interviewproject.Review.domain.ReviewComment;
import interview.interviewproject.Review.domain.ReviewCommentRequestDTO;
import interview.interviewproject.Review.repository.ReviewCommentRepository;
import interview.interviewproject.Review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReviewCommentService {

    private final ReviewCommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    public void commentSave(String nickname, Long id, ReviewCommentRequestDTO requestDTO) {
        Member member = memberRepository.findByNickname(nickname);
        Optional<Review> byId = reviewRepository.findById(id);
        Review review = byId.get();

        requestDTO.setMember(member);
        requestDTO.setReview(review);

        ReviewComment comment = requestDTO.toEntity(requestDTO);
        commentRepository.save(comment);

    }

}

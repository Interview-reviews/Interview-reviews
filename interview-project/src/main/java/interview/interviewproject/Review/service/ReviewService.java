package interview.interviewproject.Review.service;

import interview.interviewproject.Review.domain.*;
import interview.interviewproject.Review.repository.ReviewCommentRepository;
import interview.interviewproject.Review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewCommentRepository commentRepository;

    public void createPost(ReviewRequestDTO requestDTO) {
        Review review = requestDTO.toEntity(requestDTO);
        reviewRepository.save(review);
    }

    public void updatePost(ReviewRequestDTO requestDTO, Long id) {
        Optional<Review> byId = reviewRepository.findById(id);
        Review review = byId.get();
        review.Update(requestDTO);
        reviewRepository.save(review);
    }

    public ReviewResponseDTO detailPage(long post_id, String nickname) {
        Optional<Review> post = reviewRepository.findById(post_id);
        Review review = post.get();
        String post_nickname = review.getMember().getNickname();
        ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO();

        if(post_nickname.equals(nickname)) {
            reviewResponseDTO.setFlag(Boolean.TRUE);
        }
        else {
            reviewResponseDTO.setFlag(Boolean.FALSE);
        }

        List<ReviewComment> reviewCommentList = commentRepository.findByReview(post_id);
        List<ReviewCommentResponseDTO> commentResponseDTOList = new ArrayList<>();

        for(int i=0; i<reviewCommentList.size(); i++) {
            String comment = reviewCommentList.get(i).getComment();
            String nickname1 = reviewCommentList.get(i).getMember().getNickname();
            ReviewCommentResponseDTO reviewCommentResponseDTO = new ReviewCommentResponseDTO();
            reviewCommentResponseDTO.setComments(comment);
            reviewCommentResponseDTO.setNickname(nickname1);
            commentResponseDTOList.add(reviewCommentResponseDTO);
        }

        reviewResponseDTO.setCommentResponseDTOList(commentResponseDTOList);

        return ReviewResponseDTO.builder().review(review).build();
    }

    public List<ReviewResponseDTO> postListView() {
        List<Review> reviewList = reviewRepository.findAll();

        List<ReviewResponseDTO> responseDTOList = new ArrayList<>();

        for(int i=0; i<reviewList.size(); i++) {
            Review review = reviewList.get(i);
            ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO();
            ReviewResponseDTO responseDTO = ReviewResponseDTO.builder().review(review).build();
            responseDTOList.add(i, responseDTO);
        }

        return responseDTOList;
    }

    public List<ReviewResponseDTO> search(String keyword) {
        List<Review> reviewList = reviewRepository.findByTitleContaining(keyword);

        List<ReviewResponseDTO> responseDTOList = new ArrayList<>();

        for(int i=0; i<reviewList.size(); i++) {
            Review review = reviewList.get(i);
            ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO();
            ReviewResponseDTO responseDTO = ReviewResponseDTO.builder().review(review).build();
            responseDTOList.add(i, responseDTO);
        }

        return responseDTOList;
    }



}

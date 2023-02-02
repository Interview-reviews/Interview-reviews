package interview.interviewproject.Review.service;

import interview.interviewproject.Review.domain.Review;
import interview.interviewproject.Review.domain.ReviewRequestDTO;
import interview.interviewproject.Review.domain.ReviewResponseDTO;
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

    public void createPost(ReviewRequestDTO requestDTO) {
        Review review = requestDTO.toEntity(requestDTO);
        reviewRepository.save(review);
    }

    public ReviewResponseDTO detailPage(long post_id) {
        Optional<Review> post = reviewRepository.findById(post_id);
        Review review = post.get();
        ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO();

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
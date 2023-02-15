package interview.interviewproject.Review.service;

import interview.interviewproject.Member.domain.Member;
import interview.interviewproject.Member.repository.MemberRepository;
import interview.interviewproject.Review.domain.*;
import interview.interviewproject.Review.repository.LikeRepository;
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

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewCommentRepository commentRepository;
    private final LikeRepository likeRepository;

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

    // 상세페이지
    public ReviewResponseDTO detailPage(long post_id, String nickname) {
        reviewRepository.updateView(post_id);
        
        Optional<Review> post = reviewRepository.findById(post_id);
        Review review = post.get();
        String post_nickname = review.getMember().getNickname();
        ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO();

        if(post_nickname.equals(nickname)) {
            reviewResponseDTO.setUser_flag(Boolean.TRUE);
        }
        else {
            reviewResponseDTO.setUser_flag(Boolean.FALSE);
        }

        if (findLike(post_id, nickname)) {
            reviewResponseDTO.setLike_flag(Boolean.TRUE);
        }
        else {
            reviewResponseDTO.setLike_flag(Boolean.FALSE);
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

    // 상세페이지에 들어올때 좋아요가 해당 사용자가 좋아요를 눌렀는지
    public boolean findLike(Long post_id, String nickname) {
        Optional<Like> findLike = likeRepository.findByReview_IdAndMember_Nickname(post_id, nickname);

        if(findLike.isEmpty()) {
            return false;
        }
        else{
            return true;
        }
    }

    // 상세페이지 좋아요 클릭
    public boolean post_like(Long post_id, String nickname) {
        Optional<Like> findLike = likeRepository.findByReview_IdAndMember_Nickname(post_id, nickname);

        if (findLike.isEmpty()) {
            Optional<Review> byId = reviewRepository.findById(post_id);
            Review review = byId.get();
            Member member = memberRepository.findByNickname(nickname);

            Like like = Like.toEntity(member, review);
            likeRepository.save(like);

            reviewRepository.plus_Like(post_id);
            return true;
        } else {
            likeRepository.deleteByReview_IdAndMember_Nickname(post_id, nickname);

            reviewRepository.minus_Like(post_id);
            return false;
        }
    }

    // 최신순
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

    // 조회수순
    public List<ReviewResponseDTO> postListView_viewNum() {
        List<Review> reviewList = reviewRepository.findAllByOrderByView_numDesc();

        List<ReviewResponseDTO> responseDTOList = new ArrayList<>();

        for(int i=0; i<reviewList.size(); i++) {
            Review review = reviewList.get(i);
            ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO();
            ReviewResponseDTO responseDTO = ReviewResponseDTO.builder().review(review).build();
            responseDTOList.add(i, responseDTO);
        }

        return responseDTOList;
    }

    // 좋아요순
    public List<ReviewResponseDTO> postListView_likesNum() {
        List<Review> reviewList = reviewRepository.findAllByOrderByLikes_numDesc();

        List<ReviewResponseDTO> responseDTOList = new ArrayList<>();

        for(int i=0; i<reviewList.size(); i++) {
            Review review = reviewList.get(i);
            ReviewResponseDTO reviewResponseDTO = new ReviewResponseDTO();
            ReviewResponseDTO responseDTO = ReviewResponseDTO.builder().review(review).build();
            responseDTOList.add(i, responseDTO);
        }

        return responseDTOList;
    }

    // 키워드 검색
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

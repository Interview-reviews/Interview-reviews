package interview.interviewproject.Review.controller;

import interview.interviewproject.Common.annotation.LoginMember;
import interview.interviewproject.Member.domain.Member;
import interview.interviewproject.Review.domain.ReviewCommentRequestDTO;
import interview.interviewproject.Review.domain.ReviewRequestDTO;
import interview.interviewproject.Review.domain.ReviewResponseDTO;
import interview.interviewproject.Review.service.ReviewCommentService;
import interview.interviewproject.Review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewCommentService commentService;

    // 후기글 게시
    @PostMapping(value = "")
    public void createPost(@RequestBody ReviewRequestDTO requestDTO) {
        reviewService.createPost(requestDTO);
    }

    // 후기글 수정
    @PatchMapping(value = "")
    public void updatePost(@RequestBody ReviewRequestDTO requestDTO, Long id) { reviewService.updatePost(requestDTO, id);}

    // 후기글 상세페이지
    @GetMapping(value = "/detail") // 작성자가 맞을 시에 True 보내기
    public ReviewResponseDTO detailPage(Long post_id, String nickname) {
        ReviewResponseDTO reviewResponseDTO = reviewService.detailPage(post_id, nickname);
        return reviewResponseDTO;
    }

    // 후기글 상세페이지에서 좋아요 기능
    @PostMapping(value = "/detail/likes")
    public boolean post_like(Long review_id, String nickname) {
        boolean post_like = reviewService.post_like(review_id, nickname);
        return post_like;
    }

    // 후기글 리스트뷰(최신순)
    @GetMapping(value = "/post-listview")
    public List<ReviewResponseDTO> postListView() {
        List<ReviewResponseDTO> responseDTOList = reviewService.postListView();
        return responseDTOList;
    }

//     후기글 리스트뷰(조회수순)
//    public List<ReviewResponseDTO> postListView_viewNum() {
//        List<ReviewResponseDTO> responseDTOList = reviewService.postListView_viewNum();
//        return responseDTOList;
//    }
//
//     후기글 리스트뷰(좋아요순)
//    public List<ReviewResponseDTO> postListView_likesNum() {
//        List<ReviewResponseDTO> responseDTOList = reviewService.postListView_likesNum();
//        return responseDTOList;
//    }

    // 후기글 키워드 검색
    @GetMapping(value = "/keyword")
    public List<ReviewResponseDTO> keywordSearch(@RequestParam String keyword) {
        List<ReviewResponseDTO> responseDTOList = reviewService.search(keyword);
        return responseDTOList;
    }

    // 댓글 작성
    @PostMapping(value = "/{id}/comments")
    public void commentSave(@RequestBody ReviewCommentRequestDTO requestDTO, @RequestParam String nickname, @RequestParam Long post_id) {
        commentService.commentSave(nickname, post_id, requestDTO);
    }


}

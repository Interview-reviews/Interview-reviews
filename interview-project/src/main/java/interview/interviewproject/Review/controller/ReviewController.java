package interview.interviewproject.Review.controller;

import interview.interviewproject.Review.domain.ReviewCommentDTO;
import interview.interviewproject.Review.domain.ReviewDTO;
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
    public void createPost(@RequestBody ReviewDTO.Request request) {
        reviewService.createPost(request);
    }

    // 후기글 수정
    @PatchMapping(value = "")
    public void updatePost(@RequestBody ReviewDTO.Request request, Long id) { reviewService.updatePost(request, id);}

    // 후기글 상세페이지
    @GetMapping(value = "/detail") // 작성자가 맞을 시에 True 보내기
    public ReviewDTO.Response detailPage(Long post_id, String nickname) {
        ReviewDTO.Response responseDTO = reviewService.detailPage(post_id, nickname);
        return responseDTO;
    }

    // 후기글 상세페이지에서 좋아요 기능
    @PostMapping(value = "/detail/likes")
    public boolean post_like(Long review_id, String nickname) {
        boolean post_like = reviewService.post_like(review_id, nickname);
        return post_like;
    }

//    // 후기글 리스트뷰(최신순)
//    @GetMapping(value = "/post-listview")
//        public List<ReviewDTO.Response> postListView() {
//        List<ReviewDTO.Response> responseDTOList = reviewService.postListView();
//        return responseDTOList;
//    }

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
    public List<ReviewDTO.Response> keywordSearch(@RequestParam String keyword) {
        List<ReviewDTO.Response> responseDTOList = reviewService.search(keyword);
        return responseDTOList;
    }

    // 댓글 작성
    @PostMapping(value = "/comments")
    public void commentSave(@RequestBody ReviewCommentDTO requestDTO, @RequestParam String nickname, @RequestParam Long post_id) {
        commentService.commentSave(nickname, post_id, requestDTO);
    }


}

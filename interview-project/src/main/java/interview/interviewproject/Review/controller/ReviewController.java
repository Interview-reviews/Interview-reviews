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

    // 후기글 상세페이지
    @GetMapping(value = "/detail") // 작성자가 맞을 시에 True 보내기
    public ReviewResponseDTO detailPage(Long post_id) {
        ReviewResponseDTO reviewResponseDTO = reviewService.detailPage(post_id);
        return reviewResponseDTO;
    }

    // 후기글 리스트뷰
    @GetMapping(value = "/post-listview")
    public List<ReviewResponseDTO> postListView() {
        List<ReviewResponseDTO> responseDTOList = reviewService.postListView();
        return responseDTOList;
    }

    // 후기글 키워드 검색
    @GetMapping(value = "/post/keyword")
    public List<ReviewResponseDTO> keywordSearch(@RequestParam String keyword) {
        List<ReviewResponseDTO> responseDTOList = reviewService.search(keyword);
        return responseDTOList;
    }


    @PostMapping(value = "/post/{id}/comments")
    public void commentSave(@RequestBody ReviewCommentRequestDTO requestDTO, @RequestParam String nickname, @RequestParam Long post_id) {
        commentService.commentSave(nickname, post_id, requestDTO);
    }


}

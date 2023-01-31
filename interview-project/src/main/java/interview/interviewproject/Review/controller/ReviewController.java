package interview.interviewproject.Review.controller;

import interview.interviewproject.Common.annotation.LoginMember;
import interview.interviewproject.Member.domain.Member;
import interview.interviewproject.Review.domain.ReviewRequestDTO;
import interview.interviewproject.Review.domain.ReviewResponseDTO;
import interview.interviewproject.Review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/review")
public class ReviewController {

    private final ReviewService reviewService;

    // 후기글 게시
    @PostMapping(value = "/post")
    public void createPost(@RequestBody ReviewRequestDTO requestDTO) {
        reviewService.createPost(requestDTO);
    }

    // 후기글 상세페이지
    @GetMapping(value = "/detail-page")
    public ReviewResponseDTO detailPage(Long post_id) {
        ReviewResponseDTO reviewResponseDTO = reviewService.detailPage(post_id);
        return reviewResponseDTO;
    }

    // 후기글 리스트뷰
    @GetMapping(value = "/post-listview")
    public List<ReviewResponseDTO> postListView() {

    }

}

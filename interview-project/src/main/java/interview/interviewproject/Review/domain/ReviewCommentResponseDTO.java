package interview.interviewproject.Review.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ReviewCommentResponseDTO {

    private String nickname;

    private String comments;
}

package interview.interviewproject.Community.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class CommunityCommentDTO {

    private String nickname;

    private LocalDate createAt;

    private String content;

    private boolean isOwner;

    public void setOwner(boolean isOwner){
        this.isOwner = isOwner;
    }
}

package interview.interviewproject.Community.domain;

import interview.interviewproject.Member.domain.CareerType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class CommunityDTO {


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request{

        private String title;

        private String contents;

        private CommunityType category;

        private List<CommunityTagDTO> communityTagList;

    }


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{

        private Long id;

        private String nickName;

        private String title;

        private String contents;

        private CommunityType category;

        private Integer views;

        private Integer likes;

        private boolean isLiked;

        private Integer comments;

        private LocalDate createdAt;

        private List<CommunityTagDTO> communityTagList;

        private List<CommunityCommentDTO> communityCommentList;

        private CareerType careerType;

        private boolean isOwner;

        public void setOwner(boolean isOwner) {
            this.isOwner = isOwner;
        }

    }

}

package interview.interviewproject.Community.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

        private List<CommunityTag> communityTagList;

    }


    public static class Response{

    }

}

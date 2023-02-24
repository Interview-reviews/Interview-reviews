package interview.interviewproject.Member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


public class MemberDetailDTO {

        @Builder
        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Request{
                private Long detail_id;
                private List<MemberLanguageDTO.Request> language;
                private String graduate;
                private String school;
                private Double grades;
                private String major;
                private int intern;
                private String job;
                private CareerType careerType;
                private Member member;
        }




}

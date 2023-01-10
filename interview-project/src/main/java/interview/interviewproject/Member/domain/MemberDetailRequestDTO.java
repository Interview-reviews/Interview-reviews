package interview.interviewproject.Member.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;

@Getter
public class MemberDetailRequestDTO {


        private HashMap<String, String> language;
        private String school;
        private Double grades;
        private String major;
        private int intern;
        private String job;
}

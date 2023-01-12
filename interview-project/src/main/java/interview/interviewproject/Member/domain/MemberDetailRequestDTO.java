package interview.interviewproject.Member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class MemberDetailRequestDTO {


        private List<List<String>> language;
        private String graduate;
        private String school;
        private Double grades;
        private String major;
        private int intern;
        private String job;

        public MemberDetail toEntity() {
                return MemberDetail.builder()
                        .graduate(graduate)
                        .school(school)
                        .grades(grades)
                        .major(major)
                        .intern(intern)
                        .job(job)
                        .build();
        }

}

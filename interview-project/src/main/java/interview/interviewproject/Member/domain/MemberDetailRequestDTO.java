package interview.interviewproject.Member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class MemberDetailRequestDTO {


        private List<MemberLanguageDTO> language;
        private String graduate;
        private String school;
        private Double grades;
        private String major;
        private int intern;
        private String job;
        private String username;

        public static MemberDetail toEntity(MemberDetailRequestDTO requestDTO) {

                return MemberDetail.builder()
                        .school(requestDTO.getSchool())
                        .grades(requestDTO.getGrades())
                        .major(requestDTO.getMajor())
                        .intern(requestDTO.getIntern())
                        .job(requestDTO.getJob())
                        .username(requestDTO.getUsername())
                        .build();
        }
}

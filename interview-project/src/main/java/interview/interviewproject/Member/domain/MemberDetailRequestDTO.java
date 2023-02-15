package interview.interviewproject.Member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class MemberDetailRequestDTO {

        private Long id; // detail_id
        private List<MemberLanguageDTO> language;
        private String graduate;
        private String school;
        private Double grades;
        private String major;
        private int intern;
        private String job;
        private CareerType careerType;
        private Member member;

        public static MemberDetail toEntity(MemberDetailRequestDTO requestDTO) {

                return MemberDetail.builder()
                        .school(requestDTO.getSchool())
                        .grades(requestDTO.getGrades())
                        .major(requestDTO.getMajor())
                        .intern(requestDTO.getIntern())
                        .job(requestDTO.getJob())
                        .careerType(requestDTO.getCareerType())
                        .build();
        }

}

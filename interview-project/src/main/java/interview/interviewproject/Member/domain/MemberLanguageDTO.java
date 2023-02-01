package interview.interviewproject.Member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberLanguageDTO {

    private String language;

    private Long languageScore;

    public static MemberLanguage toEntity(Long id, MemberLanguageDTO languageDTO) {

        return MemberLanguage.builder()
                .id(id) // detail_id
                .language(languageDTO.getLanguage())
                .languageScore(languageDTO.getLanguageScore())
                .build();
    }
}

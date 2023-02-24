package interview.interviewproject.Member.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberLanguage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Long id;

    // 단방향 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "detail_id")
    private MemberDetail memberDetail;

    private String language;

    @Column(name = "lanuage_score")
    private Long languageScore;

    public static MemberLanguage createMemberLanguage(String language, Long languageScore) {
        return MemberLanguage.builder()
//                .memberDetail(memberDetail) // detail_id
                .language(language)
                .languageScore(languageScore)
                .build();
    }

}

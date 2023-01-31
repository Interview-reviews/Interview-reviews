package interview.interviewproject.Member.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class MemberLanguage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Long id;

    private String username;

    private String language;

    @Column(name = "lanuage_score")
    private Long languageScore;

}

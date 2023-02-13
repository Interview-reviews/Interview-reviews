package interview.interviewproject.Member.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class DetailLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Long id;

    private String language; //어학

    @Column(name = "language_score")
    private int languageScore;

}

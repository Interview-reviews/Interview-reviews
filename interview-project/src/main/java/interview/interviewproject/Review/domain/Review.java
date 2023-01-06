package interview.interviewproject.Review.domain;


import interview.interviewproject.Common.domain.BaseTimeEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Review extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String contents;

    private String nickname;

    private Double difficulty;

    private String type;

    @Column(name = "support_date")
    private LocalDate supportDate;

    private Boolean passingStatus;

    private Integer career;
}

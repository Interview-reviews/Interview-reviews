package interview.interviewproject.Member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detail_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "language_id")
    private MemberLanguage memberLanguage;

    private String school;

    private Double grades;

    private String major;

    private int intern;

    private String job; //직무

    private String username;
}

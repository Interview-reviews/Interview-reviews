package interview.interviewproject.Community.domain;

import interview.interviewproject.Member.domain.Member;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String contents;

    private CommunityType category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "community" , cascade = CascadeType.ALL)
    private List<CommunityTag> communityTagList = new ArrayList<>();

}

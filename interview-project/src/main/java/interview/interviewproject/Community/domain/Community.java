package interview.interviewproject.Community.domain;

import interview.interviewproject.Member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String contents;

    @Enumerated(value = EnumType.STRING)
    private CommunityType category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "community" , cascade = CascadeType.ALL)
    private List<CommunityTag> communityTagList = new ArrayList<>();

    public static Community createCommunity(CommunityDTO.Request request , Member member) {

        Community community = Community.builder()
                .title(request.getTitle())
                .contents(request.getContents())
                .category(request.getCategory())
                .communityTagList(request.getCommunityTagList())
                .member(member)
                .build();

        community.setCommunityTagList();
        return community;

    }

    private void setCommunityTagList() {

        for (CommunityTag communityTag : communityTagList) {
            communityTag.setCommunity(this);
        }
    }

}

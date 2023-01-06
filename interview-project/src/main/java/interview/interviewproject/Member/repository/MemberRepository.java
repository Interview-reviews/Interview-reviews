package interview.interviewproject.Member.repository;

import interview.interviewproject.Member.domain.Member;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    public Member findByNickname(String nickname) {
        return em.find(Member.class, nickname);
    }

    public Boolean nicknameDoubleCheck(String nickname) {
        Member member = em.find(Member.class, nickname);
        return member == null; // 생성가능시 True 반환
    }

    public Boolean userIdDoubleCheck(String userId) {
        Member member = em.find(Member.class, userId);
        return member == null; // 생성가능시 True 반환
    }
    // 프론트단에서 중복체크를 안했을 시에 회원가입버튼을 동작시키지 못하게 막아야하는지 확인바람

    @Transactional
    public void create_member(Member member) {
        em.persist(member);
    }
}

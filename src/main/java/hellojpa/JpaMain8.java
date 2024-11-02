package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

/**
 * 양뱡향 연관관계 주의점
 * 1. 단방향 매핑만으로도 이미 연관관계 매핑 완료
 * 2. 양방향 매핑은 반대방향으로 조회 기능이 추가 된 것 뿐
 * 3. JPQL에서 역방향으로 탐색할 일이 많음
 * 4. 단방향 매핑을 잘하고 양방향은 필요할 때 추가해도 됨
 */
public class JpaMain8 {

    public static void main(String[] args) {
        // 하나만 생성해서 애플리케이션 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 쓰레드 간 공유 x
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member3 member = new Member3();
            member.setUsername("member1");
            // member.changeTeam(team);
            em.persist(member);

            team.addMember(member);
            /**
             * team에 add 안해주면 flush, clear를 안하면값을 가져올 수가 없음
             * 그래서 넣어주는 것이 좋음
             * 즉, 순수 객체 상태를 고려해서 항상 양쪽에 값을 설정하자
             * 1. 연관 관계 편의 메소드를 생성하자.
             * 2. 양방향 매핑시에 무한 루프를 조심하자.
             */
            // team.getMembers().add(member);
            // em.flush();
            // em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            List<Member3> members = findTeam.getMembers();

            System.out.println("===========");
            for (Member3 m : members) {
                System.out.println("m.getUsername() = " + m.getUsername());
            }
            System.out.println("===========");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

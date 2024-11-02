package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

/**
 * 양뱡향 연관관계
 */
public class JpaMain7 {

    public static void main(String[] args) {
        // 하나만 생성해서 애플리케이션 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 쓰레드 간 공유 x
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            // Team에
            // @OneToMany(mappedBy = "team")
            // private List<Member3> members = new ArrayList<>();
            // 추가
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member3 member = new Member3();
            member.setUsername("member1");
            member.changeTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, 1L);
            List<Member3> members = findTeam.getMembers();
            System.out.println("findTeam.getName() = " + findTeam.getName());
            System.out.println("members.size() = " + members.size());
            for (Member3 m : members) {
                System.out.println("m.getUsername() = " + m.getUsername());
            }

            // toString에서 서로 호출 하기 때문에 무한 루프에 걸림
            System.out.println("members.toString() = " + members.toString());

            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

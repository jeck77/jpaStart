package hellojpa.run;

import hellojpa.entity.Member3;
import hellojpa.entity.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/**
 * 단방향 연관관계
 */
public class JpaMain6 {

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
            member.changeTeam(team);
            em.persist(member);

            Member3 findMember = em.find(Member3.class, member.getId());
            Team findTeam = findMember.getTeam();

            System.out.println("findTeam.getName() = " + findTeam.getName());

//            Member3 member = new Member3();
//            member.setUsername("member1");
//            member.setTeamId(team.getId());
//            em.persist(member);
//
//            Member3 findMember = em.find(Member3.class, member.getId());
//            Long findTeamId = findMember.getTeamId();
//            Team findTeam = em.find(Team.class, findTeamId);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/**
 * 즉시로딩 지연로딩
 * - 가급적 지연 로딩만 사용
 * - 즉시 로딩을 적용 하면 예상 못한 sql 발생
 * - 즉시로딩은 JPQL에서 N+1 발생
 * - OnetoMany, ManytToMany는 기본이 지연 로딩
 */
public class JpaMain16 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            //Member7 member = em.find(Member7.class, 1L);
            //printMember(member);
            // printMemberAndTeam(member);

            Team3 team3 = new Team3();
            team3.setName("team2");

            Member8 member1 = new Member8();
            member1.setUsername("hello");
            member1.setTeam3(team3);
            em.persist(member1);

            em.persist(team3);

            em.flush();
            em.clear();

            Member8 m = em.find(Member8.class, member1.getId());
            System.out.println("m = " + m);
            System.out.println("m.getClass() = " + m.getClass());
            System.out.println("m.getTeam2().getName() = " + m.getTeam3().getName());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

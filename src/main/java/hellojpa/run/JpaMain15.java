package hellojpa.run;

import hellojpa.entity.Member7;
import hellojpa.entity.Team2;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/**
 * 프록시
 * <p>
 * - em.find vs em.getReference
 * - em.find : 데이터베이스를 통해서 실제 엔티티 조회 em.getReference : 데이터베이스 조회를 미루는 가짜 엔티티 조회
 * </p>
 * 특징
 * - 프록시 객체는 처음 사용할 때 한번만 초기화
 * - 프록시 객체를 초기화 할 때, 프록시 객체가 실제 엔티티로 바뀌는 것이 아님, 프록시 객체를 통해서 실제 엔티티 접근 가능
 * - 프록시 객체는 원본 엔티티 상속, 타입체크 주의
 * - 영속성 컨텍스트에 찾는 엔티티가 있으면 실제 엔티티 반환
 * - 준영속 상태일 때 프록시를 초기화 하면 예외 발생
 */
public class JpaMain15 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            //Member7 member = em.find(Member7.class, 1L);
            //printMember(member);
            // printMemberAndTeam(member);

            Member7 member1 = new Member7();
            member1.setUsername("hello");
            em.persist(member1);

            Member7 member2 = new Member7();
            member2.setUsername("hello");
            em.persist(member2);

            em.flush();
            em.clear();

            //Member7 findMember = em.getReference(Member7.class, member1.getId());
            Member7 m1 = em.find(Member7.class, member1.getId());
            Member7 m2 = em.getReference(Member7.class, member2.getId());
            Member7 m3 = em.getReference(Member7.class, 3L);

            em.detach(m3);
            System.out.println("(m1 == m2) = " + (m1.getClass() == m2.getClass())); // 둘 다 find 일 경우 true
            // System.out.println("m3.getUsername() = " + m3.getUsername());
            logic(m1, m2);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }

    private static void logic(Member7 m1, Member7 m2) {
        System.out.println(" (m1 instanceof Member7) = " + (m1 instanceof Member7));
        System.out.println(" (m2 instanceof Member7) = " + (m2 instanceof Member7));
    }

    private static void printMember(Member7 member) {
        System.out.println("member.getUsername() = " + member.getUsername());
    }

    private static void printMemberAndTeam(Member7 member) {
        String username = member.getUsername();
        System.out.println("username = " + username);

        Team2 team = member.getTeam2();
        System.out.println("team.getMembers() = " + team.getMembers());
    }
}

package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain {
    // 테스트
    public static void main(String[] args) {
        // 하나만 생성해서 애플리케이션 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 쓰레드 간 공유 x
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            // 비영속 상태
            Member member = new Member();
            //member.setId(1L);
            //member.setName("test2");

            // 영속 상태 (객체를 저장한 상태)
            System.out.println("===== BEFORE =====");
            em.persist(member);
            System.out.println("===== AFTER  =====");
            // 회원 엔티티를 영속성 컨텍스트에서 분리
            // em.detach(member);

            // 1차 캐시에서 찾아옴 select 사용 x
            Member findMember = em.find(Member.class, 2L);
            //System.out.println("findMember.getId() = " + findMember.getId());
            //System.out.println("findMember.getName() = " + findMember.getName());

            // 같은 트랜잭션 안에 영속 엔티티의 동일성 보장
            Member member1 = em.find(Member.class, 1L);
            Member member2 = em.find(Member.class, 1L);
            System.out.println("(member1 == member2) = " + (member1 == member2));

//            member = new Member();
//            member.setId(2L);
//            member.setName("test2");
//            em.persist(member);
//
//            List<Member> result = em.createQuery("select m from Member m ", Member.class)
//                    .setFirstResult(0)
//                    .setMaxResults(2)
//                    .getResultList();
//
//            for (Member m : result) {
//                System.out.println("m.getName() = " + m.getName());
//            }

//
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());

//            findMember.setName("test2");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

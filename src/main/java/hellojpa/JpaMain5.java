package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain5 {

    public static void main(String[] args) {
        // 하나만 생성해서 애플리케이션 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 쓰레드 간 공유 x
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Member2 member = new Member2();
            // member.setId(1L);
            member.setName("test2");

            // @GeneratedValue(strategy = GenerationType.IDENTITY)로 인해
            // 기본키 전략은 commit 전에 insert 문이 실행됨 그래서 바로 getId로 가져올 수 있음
            // 시퀀스 전략은 시퀀스를 먼저 실행시켜서 멤버에 id 값을 넣어줌
            // 매번 시퀀스를 올리는 건 비효율 적이니 allocationSize 를 사용해서 미리 시퀀스 값을 가지고 있음
            em.persist(member);
            System.out.println("member.getId() = " + member.getId());

            System.out.println("================");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

// 준영속 상태
// 1차 캐시에 올라가 있는 상태
// 영속 상태에서 분리함
public class JpaMain4 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Member member = em.find(Member.class, 150L);
            member.setName("ccccc");
            // 준영속 상태로 변경 (실행 안됨)
            // detach : 특정 하나만 영속성 변경
            // clear : 모든 영속성 -> 준영속 변경
            // close : 영속성 컨텍스트를 아예 닫아버림
            em.detach(member);

            System.out.println("====================");
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

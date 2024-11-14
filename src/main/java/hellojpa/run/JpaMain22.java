package hellojpa.run;

import hellojpa.entity.Member11;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;

/**
 * Criteria
 * - 자바 표준에서 제공
 * - 동적 쿼리를 작성하기 좋음
 * - 실무에서 거의 사용 안함
 * - QueryDSL 사용 권장
 * <p>
 * QueryDSL
 * - 문자가 아닌 자바코드로 JPQL 작성 가능
 * - JPQL 빌더 역할
 * - 컴파일 시점에 문법 오류 찾기 가능
 * - 동적 쿼리 작성 편함
 * - 단순하고 쉬움
 */
public class JpaMain22 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member11> query = cb.createQuery(Member11.class);
            Root<Member11> m = query.from(Member11.class);
            CriteriaQuery<Member11> cq = query.select(m).where(cb.equal(m.get("username"), "kim"));

            List<Member11> resultList = em.createQuery(cq).getResultList();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

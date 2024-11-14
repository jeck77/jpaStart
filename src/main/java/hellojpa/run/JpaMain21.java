package hellojpa.run;

import hellojpa.entity.Member11;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import java.util.List;

/**
 * JPA 다양한 쿼리 지원
 * <p>
 * 1. JPQL
 * - 자바 코드로 짜는 것
 * <p>
 * 2. JPA Criteria
 * - JPQL을 자바 코드로 작성하도록 도와주는 빌더 클래스 API
 * <p>
 * 3. QueryDSL
 * - 자바 코드로 짜는 것
 * <p>
 * 4. 네이티브 SQL
 * - 생쿼리
 * <p>
 * <p>
 * JPQL
 * - JPA는 엔티티 객체를 중심으로 개발하기 떄문에 검색을 할 때도 테이블이 아닌 엔티티 객체를 대상으로 검색함
 * - 애플리케이션이 필요한 데이터만 불러오기 위해 조건이 포함된 SQL 필요 그래서 SQL을추상화한 JPQL이라는 객체 지향 쿼리 언어 제공
 * - JPQL => 엔티티 객체를 대상으로 쿼리
 * - SQL  => 데이터 베이스 테이블을 대상으로 쿼리
 * - 객체 지향 SQL
 * - 동적쿼리를 사용하기에는 제약 사항이 많음
 * </p>
 */
public class JpaMain21 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            List<Member11> resultList = em.createQuery("select m from Member11 m where m.username like '%mem%'",
                    Member11.class).getResultList();

            for (Member11 member11 : resultList) {
                System.out.println("==================");
                System.out.println(member11.getId());
                System.out.println("==================");
            }
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

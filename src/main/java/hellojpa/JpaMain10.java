package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/**
 * 일대일
 * - 주 테이블에 외래 키
 * 1. 주 테이블에 외래키를 두고 대상 테이블을 찾음
 * 2. 객체지향 개발자 선호
 * 3. JPA 매핑 편리
 * <p>
 * 장점 : 주 테이블만 조회해도 대상 테이블에 데이터 확인 가능
 * <br>
 * 단점 : 값이 없으면 외래키에 null 허용
 * <p>
 * - 대상 테이블에 외래키
 * 1. 대상 테이블에 외래키 존재
 * 2. 전통적인 데이터베이스 개발자 선호
 * <p>
 * 장점 : 주 테이블이 일대일 => 일대다 변경할 때 테이블 변경 x
 * <br>
 * 단점 : 프록시 기능 한계로 지연 로딩으로 설정해도 즉시 로딩 됨
 */
public class JpaMain10 {

    public static void main(String[] args) {
        // 하나만 생성해서 애플리케이션 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 쓰레드 간 공유 x
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

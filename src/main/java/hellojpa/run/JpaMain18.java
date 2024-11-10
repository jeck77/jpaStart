package hellojpa.run;

import hellojpa.entity.Address;
import hellojpa.entity.Member9;
import hellojpa.entity.Period;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/**
 * 임베디드 타입
 * - 장점 :
 * 1. 재사용
 * 2. 높은 응집도
 * 3. Period.isWork() 처럼 해당 값 타입만 사용하는 의미 있는 메소드 생성 가능
 * 4. 임베디드 타입을 포함한 모든 값 타입은 소유한 엔티티에 생명주기 의존
 * <p>
 * - 임베디드 타입과 테이블 매핑
 * 1. 임베디드 타입은 엔티티 값일 뿐
 * 2. 임베디드 타입을 사용하기 전과 후 매핑하는 테이블은 같다.
 * 3. 객체와 테이블을 아주 세밀하게 매핑하는 것이 가능
 * 4. 잘 설계한 ORM 애플리케이션은 매핑한 테이블 수보다 클래스 수가 더 많음
 */
public class JpaMain18 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Member9 member9 = new Member9();
            member9.setAddress(new Address("cirt", "street", "416-19"));
            member9.setPeriod(new Period());

            em.persist(member9);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

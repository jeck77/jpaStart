package hellojpa.run;

import hellojpa.entity.Member4;
import hellojpa.entity.Team2;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/**
 * 일대다
 * 1. 일대다 단방향은 일대다에서 일이 연관관계의 주인
 * 2. 테이블 일대드ㅏ 관계는 항상 다 쪽에 외래키가 잇음
 * 3. 객체와 테이블의 차이 때문에 반대편 테이블의 외래 키를 관리하는 특이한 구조
 * 4. JoinColumn을 사용 안하면 조인 테이블 방식을 사용함 (중간에 테이블 하나를 추가함)
 * <p>
 * 일대다 단점
 * 1. 엔티티가 관리하는 외래키가 다른 테이블에 있음
 * 2. 연관관계 관리를 위해 추가로 UPDATE SQL 실행
 * <p>
 * 정리) 일대다 단방향 매핑보다는 다대일 양방향 매핑을 사용하자!
 */
public class JpaMain9 {

    public static void main(String[] args) {
        // 하나만 생성해서 애플리케이션 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // 쓰레드 간 공유 x
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Member4 member = new Member4();
            member.setUsername("testA");
            em.persist(member);

            Team2 team = new Team2();
            team.setName("teamA");
            //
            team.getMembers().add(member);
            em.persist(team);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

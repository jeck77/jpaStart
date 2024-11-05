package hellojpa;

import hellojpa.extend.Movie3;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/**
 * 상속 관계 매핑 (추천 x)
 * <p>
 * 구현 클래스마다 테이블 전략
 * <p>
 * 장점 :
 * 1. 서브 타입을 명확하게 구분해서 처리할 떄 효과적
 * 2. Not Null 제약조건 사용 가능
 * <p>
 * 단점 :
 * 1. 여러 테이블 함께 조회할 때 성능이 느림 (UNION ALL 사용)
 * 2. 통합해서 사용하기 어려움
 */
public class JpaMain13 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Movie3 movie = new Movie3();
            movie.setDirector("aaaa");
            movie.setActor("bbbbbb");
            movie.setName("바람과 함께 사라지다111");
            movie.setPrice(100000);
            em.persist(movie);

            em.flush();
            em.clear();
            Movie3 findMovie = em.find(Movie3.class, movie.getId());
            System.out.println(findMovie.getName());
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

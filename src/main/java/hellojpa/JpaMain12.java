package hellojpa;

import hellojpa.extend.Movie2;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/**
 * 상속 관계 매핑
 * <p>
 * 단일 테이블 전략
 * <p>
 * 장점 :
 * 1. 조인이 필요 없어 조회 성능이 빠름
 * 2. 조회 쿼리가 단순함
 * <p>
 * 단점 :
 * 1. 매핑된 모든 컬럼은 Null을 허용 해줘야함
 * 2. 모든것을 저장 함으로 테이블이 커질 수도 있고 상황에 따라서 조회 성능이 느려짐
 */
public class JpaMain12 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Movie2 movie = new Movie2();
            movie.setDirector("aaaa");
            movie.setActor("bbbbbb");
            movie.setName("바람과 함께 사라지다111");
            movie.setPrice(100000);
            em.persist(movie);

            em.flush();
            em.clear();
            Movie2 findMovie = em.find(Movie2.class, movie.getId());
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

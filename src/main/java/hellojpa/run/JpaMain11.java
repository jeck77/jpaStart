package hellojpa.run;

import hellojpa.extend.Movie;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/**
 * 상속 관계 매핑
 * <p>
 * 조인 테이블 전략
 * <p>
 * 장점 :
 * 1. 테이블이 정규화 되어있다.
 * 2. 외래 키 참조 무결성 제약조건 활용 가능
 * 3. 저장 공간 효율화
 * <p>
 * 단점 :
 * 1. 조회시 조인을 많이 사용, 성능 저하
 * 2. 조회 쿼리 복잡함
 * 3. 데이터 저장시 Insert SQL 2번 호출
 */
public class JpaMain11 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Movie movie = new Movie();
            movie.setDirector("A");
            movie.setActor("bbb");
            movie.setName("바람과 함께 사라지다");
            movie.setPrice(10000);
            em.persist(movie);

            em.flush();
            em.clear();
            Movie findMovie = em.find(Movie.class, movie.getId());
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

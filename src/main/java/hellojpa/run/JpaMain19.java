package hellojpa.run;

import hellojpa.entity.Address;
import hellojpa.entity.Member9;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/**
 * 값 타입과 불변 객체 불변 객체
 * - 생성 시점 이후 절대 값을 변경할 수 없는 객체
 * - 생성자로만 값을 설정하고 수정자를 만들지 않음
 */
public class JpaMain19 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            Address address = new Address("city", "street", "416-19");

            Member9 member1 = new Member9();
            member1.setUsername("member1");
            member1.setAddress(address);
            em.persist(member1);

            Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipCode());

            Member9 member2 = new Member9();
            member2.setUsername("member2");
            // member2.setAddress(address);
            member2.setAddress(copyAddress);
            em.persist(member2);

            // 객체 타입은 참조를 전달 하기 때문에 문제 발생
            // 같은 Address를 참조시 member1만 바꿧지만 두개가 동시에 바뀌게 됨
            // 해결 방법은
            // 1. 따로 address 객체 생성 후 주입
            // 2. 불변객체
            member1.getAddress().setCity("newCity");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

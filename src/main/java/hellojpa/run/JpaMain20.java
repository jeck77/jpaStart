package hellojpa.run;

import hellojpa.entity.Address2;
import hellojpa.entity.AddressEntity;
import hellojpa.entity.Member11;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/**
 * 값 타입 컬렉션
 * 1. 값 타입을 하나 이상 저장할 때 사용
 * 2. ElementCollection 또는 CollectionTable 사용
 * 3. 데이터베이스는 컬렉션을 같은 테이블에 저장할 수 없다.
 * 4. 컬렉션을 저장하기 위한 별도의 테이블이 필요함
 * 5. 지연 로딩 전략 사용
 * 6. 영속성 전이 + 고아 객체 제거 기능을 필수로 가짐
 * - 제약사항
 * 1. 값 타입은 엔티티와 다르게 식별자 개념이 없다.
 * 2. 값은 변경하면 추적이 어렵다.
 * 3. 값 타입 컬렉션에 변경 사항이 발생하면, 주인 엔티티와 연관된 *모든 데이터 삭제* 값 타입 컬렉션에 있는 현재 값을 모두 다시 저장
 * 4. 값 타입 컬렉션을 매핑하는 테이블은 모든 컬럽을 묶어서 기본키를 구성해야 함 : null 입력 x, 중복 저장 x
 * - 대안
 * 1. 실무에서는 값 타입 컬렉션 대신 일대다 관계를 고려
 * 2. 일대다 관계를 위한 엔티티를 만들고, 여기에 값 타입 사용
 * 3. 영속성 전이 + 고아 객체 제거를 사용해서 값 타입 컬렉션 처럼 사용
 */
public class JpaMain20 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            Member11 member = new Member11();
            member.setUsername("member1");
            member.setAddress(new Address2("city", "street", "416-19"));

            member.getFavorites().add("치킨");
            member.getFavorites().add("족발");
            member.getFavorites().add("피자");

            member.getAddressHistory().add(new AddressEntity("old1", "street", "416-19"));
            member.getAddressHistory().add(new AddressEntity("old2", "street", "416-19"));

            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("================ start ===================");
            Member11 findMember = em.find(Member11.class, member.getId());
//            List<Address2> addressHistory = findMember.getAddressHistory();
//            for (Address2 address : addressHistory) {
//                System.out.println(address.getCity());
//            }

            // city => newCity
            Address2 old = findMember.getAddress();
            findMember.setAddress(new Address2(old.getCity(), "newStreet", "416-20"));

            // String 타입이기 때문에 직접 해줘야함
            // 치킨 => 한식
            findMember.getFavorites().remove("치킨");
            findMember.getFavorites().add("한식");

            // Equals, hashCode 생성
            findMember.getAddressHistory().remove(new AddressEntity("old1", "street", "416-19"));
            findMember.getAddressHistory().add(new AddressEntity("newCity11111", "street", "416-19"));
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

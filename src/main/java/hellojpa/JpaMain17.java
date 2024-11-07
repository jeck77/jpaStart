package hellojpa;

import hellojpa.extend.Child;
import hellojpa.extend.Parent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/**
 * - 영속성 전이 :
 * 1. @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
 * - 고아 객체 = 부모 엔티티와 연관 관계가 끊어진 엔티티를 자동으로 삭제 :
 * 1.@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL,orphanRemoval = true)
 * <p>
 * 주의 :
 * 1.참조 하는 곳이 하나일 때 사용
 * 2.특정 엔티티가 개인 소유할 때 사용
 */
public class JpaMain17 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {

            Child child1 = new Child();
            Child child2 = new Child();

            Parent parent = new Parent();
            parent.addchild(child1);
            parent.addchild(child2);

            em.persist(parent);
            em.flush();
            em.clear();

            Parent findParent = em.find(Parent.class, parent.getId());
            em.remove(findParent);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}

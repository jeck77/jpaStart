package hellojpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Product
 * <p>
 * 다대다
 * 1. 편리해 보이지만 실무 사용 x
 * 2. 실무에서 연결 테이블은 단순히 연결만 하고 끝나지 않음
 * 3. 주문시간, 수량 같은 데이터가 들어올 수 있음
 */
@Entity
public class Member6 extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column(name = "USERNAME")
    private String username;

    // 다대다
//    @ManyToMany
//    @JoinTable(name = "MEMBER_PRODUCT")
//    private List<Product> products = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

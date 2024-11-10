package hellojpa;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * 임베디드 타입
 */
@Entity
public class Member9 {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column(name = "USERNAME")
    private String username;

    // 기간
    @Embedded
    private Period period;

    // 주소
    @Embedded
    private Address address;

    // 직장주소
    @Embedded
    @AttributeOverride(name = "zipCode", column = @Column(name = "company_zipCode"))
    @AttributeOverride(name = "city", column = @Column(name = "company_city"))
    @AttributeOverride(name = "street", column = @Column(name = "company_street"))
    private Address workAddress;

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

package hellojpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * 기본키 매핑
 */
@Entity
//@SequenceGenerator(
//        name = “MEMBER_SEQ_GENERATOR",
//        sequenceName=“MEMBER_SEQ", //매핑할 데이터베이스 시퀀스 이름
//        initialValue = 1, allocationSize = 1)
public class Member2 {

    // @Id => 직접 할당 (Set 해줘야함)
    //
    // @GenerateValue
    // IDENTITY: 데이터베이스에 위임, MYSQL (AUTOINCREMENT)
    // SEQUENCE: 데이터베이스 시퀀스 오브젝트 사용, ORACLE
    // - @SequenceGenerato 필요
    // TABLE: 키 생성용 테이블 사용, 모든 DB에서 사용
    // - @TableGenerator 필요
    // AUTO: 방언에 따라 자동 지정, 기본값
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @GeneratedValue(strategy = GenerationType.SEQUENCE private Long id; generator = "MEMBER_SEQ_GENERATOR")
    // @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    public Member2() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
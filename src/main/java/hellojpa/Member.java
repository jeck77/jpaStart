package hellojpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
// @Table(name = "MBR")
public class Member {
    @Id
    private Long id;

    // insertable = true, updatable = false => 업데이트 불가 기본값 true
    // nullable = false => not null
    // unique = true => 유니크 제약조건 (보통 안씀 이유는 생성할 때 랜덤 이름으로 붙음 @Table 선언에서 사용)
    // columnDefinition = "varchar(100) default 'empty' 컬럼 정의"
    @Column(name = "name", nullable = false)
    private String username;

    private Integer age;

    // EnumType.STRING => enum 이름을 사용
    // EnumType.ORDINAL => enum 순서를 데이터 베이스에 저장 (사용x)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;


    private LocalDateTime createdDate2;
    private LocalDateTime lastModifiedDate2;

    @Lob
    private String description;

    // 사용안함
    @Transient
    private int temp;

    public Member() {
    }
}
package hellojpa.entity;

import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * mappedSuperClass
 * <p>
 * 1. 상속 관계 매핑 x
 * 2. 엔티티 x, 테이블과 매핑 x
 * 3. 부모 클래스를 상속 받는 자식 클래스에 매핑 정보만 제공
 * 4. 조회, 검색 불가
 * 5. 직접 생성해서 사용할 일 없음으로 추상 클래스 권장
 */
@MappedSuperclass
public class BaseEntity {
    private String createdBy;
    private LocalDateTime createdDate;
    private String lastModifiedBy;
    private LocalDateTime lastModifiedDate;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}

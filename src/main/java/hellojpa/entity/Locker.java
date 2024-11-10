package hellojpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

/**
 * 일대일
 * <p>
 * 1.다대일 양방향 매핑처럼 외래키가 있는 곳이 연관관계의 주인
 * 2.반대편은 mappedBy 적용
 */
@Entity
public class Locker {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @OneToOne(mappedBy = "locker")
    private Member5 member;
}

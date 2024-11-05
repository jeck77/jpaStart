package hellojpa.extend;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * 구현 클래스마다 테이블 전략
 */
@Entity
@DiscriminatorValue("B")
public class Book3 extends Item3 {
    private String author;
    private String isbn;
}

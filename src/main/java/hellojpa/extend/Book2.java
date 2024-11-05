package hellojpa.extend;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * 단일테이블 전략
 */
@Entity
@DiscriminatorValue("B")
public class Book2 extends Item2 {
    private String author;
    private String isbn;
}

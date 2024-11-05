package hellojpa.extend;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * 조인 전략
 */
@Entity
@DiscriminatorValue("B")
public class Book extends Item {
    private String author;
    private String isbn;
}

package hellojpa.extend;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * 단일테이블 전략
 */
@Entity
@DiscriminatorValue("A")
public class Album2 extends Item2 {
    private String artist;
}

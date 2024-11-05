package hellojpa.extend;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * 조인 전략
 */
@Entity
@DiscriminatorValue("A")
public class Album extends Item {
    private String artist;
}

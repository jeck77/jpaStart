package hellojpa.extend;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * 구현 클래스마다 테이블 전략
 */
@Entity
@DiscriminatorValue("M")
public class Movie3 extends Item3 {
    private String director;
    private String actor;

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }
}

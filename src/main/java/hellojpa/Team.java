package hellojpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Member3
 * <p>
 * 다대일
 */
@Entity
public class Team extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;

    // 양방향
    // mappedBy Member3에서 team으로 매핑이 되어있음을 알림
    // mappedBy는 조회만 가능
    @OneToMany(mappedBy = "team")
    private List<Member3> members = new ArrayList<>();

    public void addMember(Member3 member) {
        member.setTeam(this);
        members.add(member);
    }

    public List<Member3> getMembers() {
        return members;
    }

    public void setMembers(List<Member3> members) {
        this.members = members;
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

    /*@Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", members=" + members +
                '}';
    }*/
}

package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    String name;

    // mapping에서 뭐랑 연결되어 있는지를 알려주는 것.
    @OneToMany(mappedBy = "team")  // Member class의 team에 연결되있다
    private List<Member> members = new ArrayList<>();

    // 1:다 관계
//    @OneToMany
//    @JoinColumn(name = "TEAM_ID")
//    private List<Member> members = new ArrayList<>();

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

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}

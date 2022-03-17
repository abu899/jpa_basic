package hellojpa;

import javax.persistence.*;
import java.util.Date;

@Entity
//@Table(name = "MBR") // 매핑할 테이블 이름 지정
public class Member {

    @Id
    private Long id;

    @Column(name = "name") //database column name 지정
    private String username;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifedDate;

    @Lob
    private String description;

    @Transient // 특정 필드를 데이터베이스와 매핑시키고 싶지 않을 때때
   private int temp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }
}

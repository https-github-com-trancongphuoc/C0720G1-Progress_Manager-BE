package c0720g1.entity;

import javax.persistence.*;

@Entity
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    @ManyToOne
    @JoinColumn(name = "leader_id")
    Account leader;

    public Group() {
    }

    public Group(String name, Account leader) {
        this.name = name;
        this.leader = leader;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getLeader() {
        return leader;
    }

    public void setLeader(Account leader) {
        this.leader = leader;
    }
}

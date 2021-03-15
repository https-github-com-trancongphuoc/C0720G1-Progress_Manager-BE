package c0720g1.entity;

import javax.persistence.*;

@Entity
public class InfoTopicRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(columnDefinition = "TINYINT")
    Boolean status;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    Topic topic;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    Teacher teacher;
    @ManyToOne
    @JoinColumn(name = "group_id")
    Group group;

    public InfoTopicRegister() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}

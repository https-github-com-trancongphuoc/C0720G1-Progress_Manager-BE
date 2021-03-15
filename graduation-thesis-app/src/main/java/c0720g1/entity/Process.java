package c0720g1.entity;

import javax.persistence.*;

@Entity
public class Process {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(columnDefinition = "DATE")
    String dateStart;
    @Column(columnDefinition = "DATE")
    String dateEnd;
    @Column(columnDefinition = "TINYINT")
    Boolean status;
    @ManyToOne
    @JoinColumn(name = "register_topic_id")
    InfoTopicRegister infoTopicRegister;

    public Process() {
    }

    public Process(String dateStart, String dateEnd, Boolean status, InfoTopicRegister infoTopicRegister) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.status = status;
        this.infoTopicRegister = infoTopicRegister;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public InfoTopicRegister getInfoTopicRegister() {
        return infoTopicRegister;
    }

    public void setInfoTopicRegister(InfoTopicRegister infoTopicRegister) {
        this.infoTopicRegister = infoTopicRegister;
    }
}

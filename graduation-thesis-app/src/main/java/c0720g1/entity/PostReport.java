package c0720g1.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity(name = "post_report")
public class PostReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @Column(columnDefinition = "DATETIME")
    String timePostReport;
    String title;
    String content;
    @Column(name = "delete_flag")
    Boolean deleteFlag;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "register_topic_id")
    InfoTopicRegister infoTopicRegister;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "account_id")
    Account account;

    public PostReport() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimePostReport() {
        return timePostReport;
    }

    public void setTimePostReport(String timePostReport) {
        this.timePostReport = timePostReport;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public InfoTopicRegister getInfoTopicRegister() {
        return infoTopicRegister;
    }

    public void setInfoTopicRegister(InfoTopicRegister infoTopicRegister) {
        this.infoTopicRegister = infoTopicRegister;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

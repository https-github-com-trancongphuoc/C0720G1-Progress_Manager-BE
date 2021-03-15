package c0720g1.entity;

import javax.persistence.*;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(columnDefinition = "DATETIME")
    String timeNotification;
    @ManyToOne
    @JoinColumn(name = "account_id")
    Account account;
    String content;

    public Notification() {
    }

    public Notification(String timeNotification, Account account, String content) {
        this.timeNotification = timeNotification;
        this.account = account;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimeNotification() {
        return timeNotification;
    }

    public void setTimeNotification(String timeNotification) {
        this.timeNotification = timeNotification;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

package com.codegym.entity;

import javax.persistence.*;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "DATETIME")
    private String timeNotification;
    private String content;
    private String title;
    // Kiểm tra xem người dùng đã xem thông báo hay chưa.
    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "account_send_notification_id", referencedColumnName = "id")
    private Account accountSendNotification;

    public Notification() {
    }

    public Notification(Integer id, String timeNotification, String content, String title, Boolean status, Account account, Account accountSendNotification) {
        this.id = id;
        this.timeNotification = timeNotification;
        this.content = content;
        this.title = title;
        this.status = status;
        this.account = account;
        this.accountSendNotification = accountSendNotification;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccountSendNotification() {
        return accountSendNotification;
    }

    public void setAccountSendNotification(Account accountSendNotification) {
        this.accountSendNotification = accountSendNotification;
    }
}

package com.codegym.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "info_topic_register")
public class InfoTopicRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Kiểm tra xem đề tài đã được xét duyệt hay chưa.
    private Boolean status;

    // Kiểm tra xem đã hoàn thành đề tài này hay chưa
    private Boolean statusComplete;

    @ManyToOne
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    private Topic topic;

    @ManyToOne
    @JoinColumn(name = "group_account_id", referencedColumnName = "id")
    private GroupAccount groupAccount;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;

    @OneToMany(mappedBy = "infoTopicRegister")
    private List<TopicProcess> processList;

    public InfoTopicRegister() {
    }

    public InfoTopicRegister(Integer id, Boolean status, Boolean statusComplete, Topic topic, GroupAccount groupAccount, Teacher teacher, List<TopicProcess> processList) {
        this.id = id;
        this.status = status;
        this.statusComplete = statusComplete;
        this.topic = topic;
        this.groupAccount = groupAccount;
        this.teacher = teacher;
        this.processList = processList;
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

    public Boolean getStatusComplete() {
        return statusComplete;
    }

    public void setStatusComplete(Boolean statusComplete) {
        this.statusComplete = statusComplete;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public GroupAccount getGroupAccount() {
        return groupAccount;
    }

    public void setGroupAccount(GroupAccount groupAccount) {
        this.groupAccount = groupAccount;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<TopicProcess> getProcessList() {
        return processList;
    }

    public void setProcessList(List<TopicProcess> processList) {
        this.processList = processList;
    }
}

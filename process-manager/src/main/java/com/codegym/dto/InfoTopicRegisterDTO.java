package com.codegym.dto;

import java.util.List;

public class InfoTopicRegisterDTO {
    private Integer id;
    private Boolean status;
    private Boolean statusComplete;
    private Boolean topicCancel;
    private String descriptionURL;
    private Integer topicId;
    private Integer groupAccountId;
    private Integer teacherId;
    private String content;
    private String url;
    private List<StudentDTO> studentList;
    private String deadline;

    public InfoTopicRegisterDTO() {
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

    public Boolean getTopicCancel() {
        return topicCancel;
    }

    public void setTopicCancel(Boolean topicCancel) {
        this.topicCancel = topicCancel;
    }

    public String getDescriptionURL() {
        return descriptionURL;
    }

    public void setDescriptionURL(String descriptionURL) {
        this.descriptionURL = descriptionURL;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getGroupAccountId() {
        return groupAccountId;
    }

    public void setGroupAccountId(Integer groupAccountId) {
        this.groupAccountId = groupAccountId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<StudentDTO> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<StudentDTO> studentList) {
        this.studentList = studentList;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }
}

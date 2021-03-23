package com.codegym.dto;

public class CommentPostDTO {

    Integer id;
    String content;
    String title;
    String timeComment;
    Boolean deleteFlag;
    Boolean status;
    Integer accountId;
    Integer topicProcessId;
    Integer replyCommentId;
    String email;
    Integer processDetailId;
    Integer teacherId;
//    private Integer percentProcess;
//    private Integer idProcess;
    ;

    public CommentPostDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTimeComment() {
        return timeComment;
    }

    public void setTimeComment(String timeComment) {
        this.timeComment = timeComment;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getTopicProcessId() {
        return topicProcessId;
    }

    public void setTopicProcessId(Integer topicProcessId) {
        this.topicProcessId = topicProcessId;
    }

    public Integer getReplyCommentId() {
        return replyCommentId;
    }

    public void setReplyCommentId(Integer replyCommentId) {
        this.replyCommentId = replyCommentId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getProcessDetailId() {
        return processDetailId;
    }

    public void setProcessDetailId(Integer processDetailId) {
        this.processDetailId = processDetailId;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
}

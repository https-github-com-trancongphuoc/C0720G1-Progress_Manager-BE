package c0720g1.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(columnDefinition = "DATETIME")
    String timeComment;
    @Column(name = "delete_flag")
    Boolean deleteFlag;
    String content;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn (name = "comment_id")
    Comment comment;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "account_id")
    Account account;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "post_report_id")
    PostReport postReport;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "process_id")
    ProcessGraduate processGraduate;

    public Comment() {
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimeComment() {
        return timeComment;
    }

    public void setTimeComment(String timeComment) {
        this.timeComment = timeComment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public PostReport getPostReport() {
        return postReport;
    }

    public void setPostReport(PostReport postReport) {
        this.postReport = postReport;
    }

    public ProcessGraduate getProcessGraduate() {
        return processGraduate;
    }

    public void setProcessGraduate(ProcessGraduate processGraduate) {
        this.processGraduate = processGraduate;
    }
}

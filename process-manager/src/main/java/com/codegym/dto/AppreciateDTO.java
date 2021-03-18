package com.codegym.dto;

import java.util.List;

public class AppreciateDTO {
    private String content;
    private Integer percentProcess;
    private Integer idProcess;
    private Integer idProcessDetail;
    private Integer idAccount;
    private List<StudentDTO> studentList;

    public AppreciateDTO() {
    }

    public AppreciateDTO(String content, Integer percentProcess, Integer idProcess, Integer idProcessDetail, Integer idAccount, List<StudentDTO> studentList) {
        this.content = content;
        this.percentProcess = percentProcess;
        this.idProcess = idProcess;
        this.idProcessDetail = idProcessDetail;
        this.idAccount = idAccount;
        this.studentList = studentList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPercentProcess() {
        return percentProcess;
    }

    public void setPercentProcess(Integer percentProcess) {
        this.percentProcess = percentProcess;
    }

    public Integer getIdProcess() {
        return idProcess;
    }

    public void setIdProcess(Integer idProcess) {
        this.idProcess = idProcess;
    }

    public Integer getIdProcessDetail() {
        return idProcessDetail;
    }

    public void setIdProcessDetail(Integer idProcessDetail) {
        this.idProcessDetail = idProcessDetail;
    }

    public Integer getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Integer idAccount) {
        this.idAccount = idAccount;
    }

    public List<StudentDTO> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<StudentDTO> studentList) {
        this.studentList = studentList;
    }
}

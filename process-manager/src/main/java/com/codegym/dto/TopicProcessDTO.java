package com.codegym.dto;

public class TopicProcessDTO {
    private Integer id;
    private String dateStart;
    private String dateEnd;
    private Boolean status;
    private Integer processNumber;
    private Integer percentProcess;
    private Integer infoTopicRegister;

    public TopicProcessDTO() {
    }

    public TopicProcessDTO(Integer id, String dateStart, String dateEnd, Boolean status, Integer processNumber, Integer percentProcess, Integer infoTopicRegister) {
        this.id = id;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.status = status;
        this.processNumber = processNumber;
        this.percentProcess = percentProcess;
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

    public Integer getProcessNumber() {
        return processNumber;
    }

    public void setProcessNumber(Integer processNumber) {
        this.processNumber = processNumber;
    }

    public Integer getPercentProcess() {
        return percentProcess;
    }

    public void setPercentProcess(Integer percentProcess) {
        this.percentProcess = percentProcess;
    }

    public Integer getInfoTopicRegister() {
        return infoTopicRegister;
    }

    public void setInfoTopicRegister(Integer infoTopicRegister) {
        this.infoTopicRegister = infoTopicRegister;
    }
}

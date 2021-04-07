package com.codegym.dto;

import com.codegym.entity.InfoTopicRegister;
import com.codegym.entity.Student;

import java.util.List;

public class GroupAccountDTO {
    private Integer id;
    private String name;
    private boolean deleteFlag;
    private boolean status;
    private String date;
    private List<StudentDTO> studentList;
    private List<InfoTopicRegisterDTO> infoTopicRegisterList;
    private Integer teacherId;

    public GroupAccountDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StudentDTO> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<StudentDTO> studentList) {
        this.studentList = studentList;
    }

    public List<InfoTopicRegisterDTO> getInfoTopicRegisterList() {
        return infoTopicRegisterList;
    }

    public void setInfoTopicRegisterList(List<InfoTopicRegisterDTO> infoTopicRegisterList) {
        this.infoTopicRegisterList = infoTopicRegisterList;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
}

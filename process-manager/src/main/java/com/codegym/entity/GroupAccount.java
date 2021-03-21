package com.codegym.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.List;
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@Table(name = "group_account")
public class GroupAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private boolean delete_flag;

    @OneToMany(mappedBy = "groupAccount")
    private List<Student> studentList;

    @OneToMany(mappedBy = "groupAccount")
    private List<InfoTopicRegister> infoTopicRegisterList;

    public GroupAccount() {
    }

    public GroupAccount(Integer id, String name, boolean delete_flag, List<Student> studentList, List<InfoTopicRegister> infoTopicRegisterList) {
        this.id = id;
        this.name = name;
        this.delete_flag = delete_flag;
        this.studentList = studentList;
        this.infoTopicRegisterList = infoTopicRegisterList;
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

    public boolean isDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(boolean delete_flag) {
        this.delete_flag = delete_flag;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<InfoTopicRegister> getInfoTopicRegisterList() {
        return infoTopicRegisterList;
    }

    public void setInfoTopicRegisterList(List<InfoTopicRegister> infoTopicRegisterList) {
        this.infoTopicRegisterList = infoTopicRegisterList;
    }
}

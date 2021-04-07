package com.codegym.dto;

import com.codegym.entity.AccountRole;

import java.util.List;

public class AccountDTO {
    private Integer id;
    private String username;
    private String password;
    private List<AccountRole> accountRoleList;
    private TeacherCreateDTO teacher;
    private StudentCreateDTO student;

    public AccountDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AccountRole> getAccountRoleList() {
        return accountRoleList;
    }

    public void setAccountRoleList(List<AccountRole> accountRoleList) {
        this.accountRoleList = accountRoleList;
    }

    public TeacherCreateDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherCreateDTO teacher) {
        this.teacher = teacher;
    }

    public StudentCreateDTO getStudent() {
        return student;
    }

    public void setStudent(StudentCreateDTO student) {
        this.student = student;
    }
}

package com.codegym.dto;

import com.codegym.entity.Account;
import com.codegym.entity.Grade;
import com.codegym.entity.GroupAccount;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

public class StudentDTO {
    private Integer id;
    private String name;
    private String dateOfBirth;
    private String address;
    private String phone;
    private String email;
    private String avatar;
    private Boolean gender;
    private Grade grade;
    private Account account;

    public StudentDTO() {
    }

    public StudentDTO(Integer id, String name, String dateOfBirth, String address, String phone, String email, String avatar, Boolean gender, Grade grade, Account account) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.avatar = avatar;
        this.gender = gender;
        this.grade = grade;
        this.account = account;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

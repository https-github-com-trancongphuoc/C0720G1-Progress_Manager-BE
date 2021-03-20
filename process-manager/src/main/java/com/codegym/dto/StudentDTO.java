package com.codegym.dto;

public class StudentDTO {
    private String name;
    private String email;
    private String image;
    private String address;
    private String dateOfBirth;
    private String phone;
    private Integer grade;
    private Integer faculty;

    public StudentDTO() {
    }

    public StudentDTO(String name, String email, String image, String address, String dateOfBirth, String phone, Integer grade, Integer faculty) {
        this.name = name;
        this.email = email;
        this.image = image;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.grade = grade;
        this.faculty = faculty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getFaculty() {
        return faculty;
    }

    public void setFaculty(Integer faculty) {
        this.faculty = faculty;
    }
}

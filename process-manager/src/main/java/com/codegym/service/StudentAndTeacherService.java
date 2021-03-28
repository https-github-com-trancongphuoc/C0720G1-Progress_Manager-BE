package com.codegym.service;


import com.codegym.dto.StudentCreateDTO;
import com.codegym.dto.TeacherCreateDTO;

public interface StudentAndTeacherService {
    void createStudent(StudentCreateDTO studentCreateDTO);

    void createTeacher(TeacherCreateDTO teacherCreateDTO);
}

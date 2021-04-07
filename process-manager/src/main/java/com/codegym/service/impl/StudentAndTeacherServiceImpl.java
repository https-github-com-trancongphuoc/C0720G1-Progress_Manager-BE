package com.codegym.service.impl;

import com.codegym.dto.StudentCreateDTO;
import com.codegym.dto.TeacherCreateDTO;
import com.codegym.repository.StudentTeacherRepository;
import com.codegym.service.StudentAndTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentAndTeacherServiceImpl implements StudentAndTeacherService {
    @Autowired
    StudentTeacherRepository studentTeacherRepository;

    @Override
    public void createStudent(StudentCreateDTO studentCreateDTO) {
        studentTeacherRepository.createStudent(
                studentCreateDTO.getAddress(), studentCreateDTO.getDateOfBirth(), studentCreateDTO.getEmail(),
                studentCreateDTO.getGender(), studentCreateDTO.getName(), studentCreateDTO.getPhone(), studentCreateDTO.getGradeId(), true, studentCreateDTO.getAccountId());
    }

    @Override
    public void createTeacher(TeacherCreateDTO teacherCreateDTO) {
        studentTeacherRepository.createTeacher(
                teacherCreateDTO.getAddress(), teacherCreateDTO.getDateOfBirth(), teacherCreateDTO.getEmail(), teacherCreateDTO.getGender(),
                teacherCreateDTO.getName(), teacherCreateDTO.getPhone(), teacherCreateDTO.getDegreeId(), teacherCreateDTO.getFacultyId(), true, teacherCreateDTO.getAccountId()
        );
    }


}

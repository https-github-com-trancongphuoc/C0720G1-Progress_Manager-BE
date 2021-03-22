package com.codegym.service.impl;

import com.codegym.dto.CreateUpdateTeacherDTO;
import com.codegym.entity.Teacher;
import com.codegym.repository.TeacherRepository;
import com.codegym.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class  TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public Page<Teacher> getAllTeacher(String find, Pageable pageable) {
        return teacherRepository.getAllTeacher(find,pageable);
    }

    @Override
    public void createTeacher(CreateUpdateTeacherDTO createUpdateTeacherDTO) {
        teacherRepository.createTeacher(createUpdateTeacherDTO.getAddress(), createUpdateTeacherDTO.getAvatar(), createUpdateTeacherDTO.getDateOfBirth(), createUpdateTeacherDTO.getEmail(),
                createUpdateTeacherDTO.getName(), createUpdateTeacherDTO.getPhone(), createUpdateTeacherDTO.getDegree(), createUpdateTeacherDTO.getFaculty());
    }

    @Override
    public void deleteTeacherById(Integer id) {
        teacherRepository.deleteStudent(id);
    }
}

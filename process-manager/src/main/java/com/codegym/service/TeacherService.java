package com.codegym.service;

import com.codegym.dto.CreateUpdateTeacherDTO;
import com.codegym.dto.ITeacherEditDTO;
import com.codegym.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TeacherService {

    Page<Teacher> getAllTeacher(String find, Pageable pageable);

    void createTeacher(CreateUpdateTeacherDTO createUpdateTeacherDTO);

    void deleteTeacherById(Integer id);

    ITeacherEditDTO findTeacherById(Integer id);

    void editTeacher(CreateUpdateTeacherDTO createUpdateTeacherDTO);

}

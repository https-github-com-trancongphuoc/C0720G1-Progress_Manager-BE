package com.codegym.service;

import com.codegym.dto.CreateUpdateStudentDTO;
import com.codegym.dto.IStudentEditDTO;
import com.codegym.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StudentService {


    /**
     * TinVT
     * Find all student
     */
    Page<Student> findAllStudent(String find, Pageable pageable);

    /**
     * TinVT
     * Delete Student By id
     */
    void deleteStudentById(Integer id);

    /**
     * TinVT
     * Edit Student
     */
    void editStudent(CreateUpdateStudentDTO studentDTO);

    /**
     * TinVT
     * Create New Student
     */
    void createNewStudent(CreateUpdateStudentDTO studentDTO);

    /**
     * TinVT
     * Find By Id
     */
    IStudentEditDTO findStudentById(Integer id);
}

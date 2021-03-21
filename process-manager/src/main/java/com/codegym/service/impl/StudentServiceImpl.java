package com.codegym.service.impl;

import com.codegym.dto.IStudentDTO;
import com.codegym.dto.StudentDTO;
import com.codegym.entity.Student;
import com.codegym.repository.StudentRepository;
import com.codegym.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    /**
     * TinVT
     * Find all student
     */
    @Override
    public Page<Student> findAllStudent(String find, Pageable pageable) {
        return studentRepository.getAllStudent(find, pageable);
    }

    /**
     * TinVT
     * Delete Student By id
     */
    @Override
    public void deleteStudentById(Integer id) {
        studentRepository.deleteStudent(id);
    }


    /**
     * TinVT
     * Edit Student
     */
    @Override
    public void editStudent(StudentDTO studentDTO) {
//        studentRepository.editStudent(studentDTO.getName(), studentDTO.getEmail(), studentDTO.getAvatar(), studentDTO.getAddress(),
//                studentDTO.getDayOfBirth(), studentDTO.getGender(), studentDTO.getId());
    }


    /**
     * TinVT
     * Create New Student
     */
    @Override
    public void createNewStudent(StudentDTO studentDTO) {
        studentRepository.addNewStudent(studentDTO.getName(), studentDTO.getEmail(), studentDTO.getAvatar(), studentDTO.getAddress(),
                studentDTO.getDateOfBirth(), studentDTO.getPhone(), studentDTO.getGrade().getId());
    }

}

package com.codegym.repository;

import com.codegym.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Controller
@Transactional
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    @Query(value = "select * from teacher join degree on teacher.degree_id = degree.id join faculty on teacher.faculty_id = faculty.id" +
            " where concat('MGV-',teacher.id,ifnull(teacher.address,''),ifnull(teacher.date_of_birth,''),ifnull(teacher.phone,''),ifnull(teacher.email,''),ifnull(teacher.name,''),degree.name,faculty.name) like %?1%",nativeQuery = true)
    Page<Teacher> getAllTeacher(String find, Pageable pageable);


    @Modifying
    @Query(value = "insert  into teacher(teacher.address, teacher.avatar, teacher.date_of_birth, teacher.email, teacher.name, teacher.phone," +
            "teacher.degree_id, teacher.faculty_id) value (?1,?2,?3,?4,?5,?6,?7,?8)",nativeQuery = true)
    void createTeacher(String address, String avatar, String dateOfBirth, String email, String name, String phone, Integer degreeId, Integer facultyId);


    @Modifying
    @Query(value = "DELETE from teacher where teacher.id = ?1",nativeQuery = true)
    void deleteStudent(Integer id);
}

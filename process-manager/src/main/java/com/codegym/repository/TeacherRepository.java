package com.codegym.repository;

import com.codegym.dto.ITeacherEditDTO;
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
            " where concat('MGV-',teacher.id,ifnull(teacher.address,''),ifnull(teacher.date_of_birth,''),ifnull(teacher.phone,''),ifnull(teacher.email,''),ifnull(teacher.name,''),degree.name,faculty.name) like %?1% and teacher.delete_flag = 1" ,nativeQuery = true)
    Page<Teacher> getAllTeacher(String find, Pageable pageable);


    @Modifying
    @Query(value = "insert  into teacher(teacher.address, teacher.avatar, teacher.date_of_birth, teacher.email, teacher.name, teacher.phone," +
            "teacher.degree_id, teacher.faculty_id, teacher.gender, teacher.delete_flag) value (?1,?2,?3,?4,?5,?6,?7,?8,?9, true )",nativeQuery = true)
    void createTeacher(String address, String avatar, String dateOfBirth, String email, String name, String phone, Integer degreeId, Integer facultyId, Boolean gender);


    @Modifying
    @Query(value = "update teacher set teacher.delete_flag = 0 where teacher.id = ?1",nativeQuery = true)
    void deleteTeacher(Integer id);

    @Query(value = "select teacher.id as id, teacher.address as address, teacher.avatar as avatar, teacher.date_of_birth as dateOfBirth," +
            "teacher.email as email, teacher.name as name, teacher.phone as phone, teacher.degree_id as degree, teacher.faculty_id as faculty," +
            "teacher.gender as gender from teacher where teacher.id = ?1 and teacher.delete_flag = true", nativeQuery = true)
    ITeacherEditDTO getTeacherById(Integer id);

    @Modifying
    @Query(value = "update teacher set teacher.address = ?1, teacher.avatar = ?2, teacher.date_of_birth = ?3, teacher.email = ?4, teacher.name = ?5, teacher.phone = ?6, teacher.degree_id = ?7," +
            "teacher.faculty_id = ?8, teacher.gender = ?9 where teacher.id = ?10", nativeQuery = true)
    void editTeacher(String address, String avatar, String dateOfBirth, String email, String name, String phone, Integer degree, Integer faculty, Boolean gender, Integer id);

}

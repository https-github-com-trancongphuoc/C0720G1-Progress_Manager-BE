package com.codegym.repository;

import com.codegym.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface StudentTeacherRepository extends JpaRepository<Student, Integer> {
    @Modifying
    @Query(value = "INSERT INTO `process_manager`.`student` " +
            "(`address`, `date_of_birth`, `email`, `gender`, `name`, `phone`, `grade_id`, `delete_flag`) " +
            "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8) ", nativeQuery = true)
    void createStudent(String address, String dateOfBirth, String email, Boolean gender, String name, String phone, Integer gradeId, Boolean delete);

    @Modifying
    @Query(value = "INSERT INTO `process_manager`.`teacher` " +
            "(`address`, `date_of_birth`, `email`, `gender`, `name`, `phone`, `degree_id`, `faculty_id`, `delete_flag`) " +
            "VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9)", nativeQuery = true)
    void createTeacher(String address, String dateOfBirth, String email, Boolean gender, String name, String phone, Integer degreeId, Integer facultyId, Boolean delete);
}

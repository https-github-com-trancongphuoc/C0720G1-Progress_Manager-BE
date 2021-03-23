package com.codegym.repository;

import com.codegym.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface StudentTopicRepository extends JpaRepository<Student, Integer> {

    @Query(value = "select * from student where group_account_id = ?1", nativeQuery = true)
    List<Student> findAllStudent(Integer id);
}

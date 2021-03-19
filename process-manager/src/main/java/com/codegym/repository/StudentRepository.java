package com.codegym.repository;

import com.codegym.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Page<Student> findAll(Pageable pageable);

    Page<Student> findAllByGroupAccountIsNull(Pageable pageable);

    @Query(
            value = "select *\n" +
                    "from student st \n" +
                    "where group_account_id is null\n" +
                    "having concat(st.id,st.`name`) like %?1%",
            nativeQuery = true)
    Page<Student> searchStudent(String name, Pageable pageable);



}

package com.codegym.repository;
import com.codegym.dto.IStudentDTO;
import com.codegym.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface StudentRepository extends JpaRepository<Student, Integer> {

 /**
  * TinVT
  * Delete Student By id
  */
 @Query(value = "SELECT * from student join grade on student.grade_id = grade.id join faculty on grade.faculty_id = faculty.id WHERE CONCAT('MSV-',student.id,student.name,student.address, student.date_of_birth, student.email, student.phone, grade.name, faculty.name) LIKE %?1% ", nativeQuery = true)
 Page<Student> getAllStudent(String find, Pageable pageable);

 /**
  * TinVT
  * Delete Student By id
  */
 @Modifying
 @Query(value = "DELETE from student where student.id = ?1",nativeQuery = true)
 void deleteStudent(Integer id);

 /**
  * TinVT
  * Edit Student
  */
 @Modifying
 @Query(value = "update student set student.name = ?1, student.email = ?2, student.avatar = ?3, student.address = ?4" +
   "student.date_of_birth = ?5, student.gender = ?6 where student.id=?7",nativeQuery = true)
 void editStudent(String name, String email, String avatar, String address, String dayOfBirth, Boolean gender, Integer id);

 /**
  * TinVT
  * Add New Student
  */
 @Modifying
 @Query(value = "insert into student(student.name, student.email, student.avatar, student.address, " +
   " student.date_of_birth, student.phone, student.grade_id)" +
   "values(?1,?2,?3,?4,?5,?6,?7) ",nativeQuery = true)
 void addNewStudent(String name, String email, String avatar, String address, String dayOfBirth,String phone, Integer grade);


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

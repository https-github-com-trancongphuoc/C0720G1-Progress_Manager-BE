package com.codegym.repository;
import com.codegym.dto.IStudentEditDTO;
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
     * Get All Student
     */
    @Query(value = "SELECT * from student join grade on student.grade_id = grade.id join faculty on grade.faculty_id = faculty.id " +
            "WHERE CONCAT('MSV-',student.id,ifnull(student.name,''),ifnull(student.address,''),ifnull(student.date_of_birth,''),ifnull(student.email,''),ifnull(student.phone,''), faculty.name) LIKE %?1% ", nativeQuery = true)
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
    @Query(value = "update student set student.name = ?1, student.email = ?2, student.avatar = ?3, student.address = ?4, " +
            "student.date_of_birth = ?5, student.phone =?6, student.grade_id = ?7,student.gender = ?8 where student.id=?9 ",nativeQuery = true)
    void editStudent(String name, String email, String avatar, String address, String dayOfBirth, String phone,Integer grade,Boolean gender, Integer id);

    /**
     * TinVT
     * Add New Student
     */
    @Modifying
    @Query(value = "insert into student(student.name, student.email, student.avatar, student.address, " +
            " student.date_of_birth, student.phone, student.grade_id, student.gender)" +
            "values(?1,?2,?3,?4,?5,?6,?7,?8) ",nativeQuery = true)
    void addNewStudent(String name, String email, String avatar, String address, String dayOfBirth,String phone, Integer grade, Boolean gender);

    /**
     * TinVT
     * find By Id
     */
    @Query(value = "select student.id as id, student.name as name, student.date_of_birth as dateOfBirth, student.phone as phone," +
            "student.grade_id as grade, student.address as address, student.email as email, student.avatar as image, student.gender as gender from student where student.id = ?1", nativeQuery = true)
    IStudentEditDTO findStudentById(Integer id);


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

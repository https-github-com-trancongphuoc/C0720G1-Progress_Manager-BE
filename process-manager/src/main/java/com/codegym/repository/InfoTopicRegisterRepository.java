package com.codegym.repository;

import com.codegym.entity.InfoTopicRegister;
import com.codegym.entity.Student;
import com.codegym.entity.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface InfoTopicRegisterRepository extends JpaRepository<InfoTopicRegister, Integer> {


    @Query(value = "SELECT * FROM process_manager.info_topic_register " +
            "join (select * from student where student.group_account_id = ?1) AS student_tmp " +
            "on student_tmp.group_account_id = info_topic_register.group_account_id", nativeQuery = true)
    InfoTopicRegister findAllByGroupAccount(Integer id);

    InfoTopicRegister findByGroupAccount_IdAndStatusCompleteFalse(Integer groupAccount_id);

    Page<InfoTopicRegister> findAllByTopic_Faculty_IdAndStatusFalseAndTopicCancelFalse(Integer topic_faculty_id, Pageable pageable);

    @Modifying
    @Query(value = "UPDATE `process_manager`.`info_topic_register` SET `topic_cancel` = ?1 WHERE (`id` = ?2) ", nativeQuery = true)
    void topicCancel(Boolean topicCancel, Integer id);

    @Query(value = "select teacher.email from info_topic_register " +
            "join teacher on teacher.id = info_topic_register.teacher_id " +
            "join topic_process on topic_process.info_topic_register = info_topic_register.id " +
            "where topic_process.date_end >= curdate()+1 and topic_process.date_end <= curdate()+2 ", nativeQuery = true)
    List<String> listEmailTeacher();

    @Query(value = "select student.email from info_topic_register " +
            "join group_account on group_account.id = info_topic_register.group_account_id " +
            "join student on student.group_account_id = group_account.id " +
            "join topic_process on topic_process.info_topic_register = info_topic_register.id " +
            "where topic_process.date_end >= curdate()+1 and topic_process.date_end <= curdate()+2", nativeQuery = true)
    List<String> listEmailStudent();

    @Query(value = "select teacher.email from info_topic_register " +
            "join teacher on teacher.id = info_topic_register.teacher_id " +
            "join topic_process on topic_process.info_topic_register = info_topic_register.id " +
            "where topic_process.date_end >= curdate()+1 " +
            "and topic_process.date_end <= curdate()+2 " +
            "and percent_process = 100", nativeQuery = true)
    List<String> listEmailTeacherFinish();

    @Query(value = "select student.email from info_topic_register " +
            "join group_account on group_account.id = info_topic_register.group_account_id " +
            "join student on student.group_account_id = group_account.id " +
            "join topic_process on topic_process.info_topic_register = info_topic_register.id " +
            "where topic_process.date_end >= curdate()+1 " +
            "and topic_process.date_end <= curdate()+2 " +
            "and percent_process = 100", nativeQuery = true)
    List<String> listEmailStudentFinish();

    @Query(value = "select student.email from group_account " +
            "join student on student.group_account_id = group_account.id " +
            "where group_account.date >= curdate()+1 " +
            "and group_account.date <= curdate()+2", nativeQuery = true)
    List<String> listEmailStudentDeadline();

    @Query(value = "select account.id from info_topic_register " +
            "join teacher on teacher.id = info_topic_register.teacher_id " +
            "join topic_process on topic_process.info_topic_register = info_topic_register.id " +
            "join account on teacher.account_id = account.id " +
            "where topic_process.date_end >= curdate()+1 and topic_process.date_end <= curdate()+2 ", nativeQuery = true)
    List<Integer> listIdTeacher();

    @Query(value = "select account.id from info_topic_register " +
            "join group_account on group_account.id = info_topic_register.group_account_id " +
            "join student on student.group_account_id = group_account.id " +
            "join topic_process on topic_process.info_topic_register = info_topic_register.id " +
            "join account on student.account_id = account.id " +
            "where topic_process.date_end >= curdate()+1 and topic_process.date_end <= curdate()+2", nativeQuery = true)
    List<Integer> listIdStudent();

    @Query(value = "select account.id from info_topic_register " +
            "join teacher on teacher.id = info_topic_register.teacher_id " +
            "join topic_process on topic_process.info_topic_register = info_topic_register.id " +
            "join account on teacher.account_id = account.id " +
            "where topic_process.date_end >= curdate()+1 " +
            "and topic_process.date_end <= curdate()+2 " +
            "and percent_process = 100", nativeQuery = true)
    List<Integer> listIdTeacherFinish();

    @Query(value = "select account.id from info_topic_register " +
            "join group_account on group_account.id = info_topic_register.group_account_id " +
            "join student on student.group_account_id = group_account.id " +
            "join topic_process on topic_process.info_topic_register = info_topic_register.id " +
            "join account on student.account_id = account.id " +
            "where topic_process.date_end >= curdate()+1 " +
            "and topic_process.date_end <= curdate()+2 " +
            "and percent_process = 100", nativeQuery = true)
    List<Integer> listIdStudentFinish();

    @Query(value = "select account.id from group_account " +
            "join student on student.group_account_id = group_account.id " +
            "join account on student.account_id = account.id " +
            "where group_account.date >= curdate()+1 " +
            "and group_account.date <= curdate()+2", nativeQuery = true)
    List<Integer> listIdStudentDeadline();
}

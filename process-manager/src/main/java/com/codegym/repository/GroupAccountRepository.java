package com.codegym.repository;

import com.codegym.dto.CheckJoinGroupDTO;
import com.codegym.dto.StudentInformation;
import com.codegym.entity.GroupAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface GroupAccountRepository extends JpaRepository<GroupAccount, Integer> {
    @Modifying
    @Query(
            value = "insert into group_account(`name`,delete_flag,status) value (?1,false,false)",
            nativeQuery = true)
    void saveGroup(String name);

    @Modifying
    @Query(
            value = "update account_role \n" +
                    "set account_role.role_id = 3\n" +
                    "where account_role.account_id = ?1",
            nativeQuery = true)
    void setLeaderGroup(Integer leader_id);

    @Modifying
    @Query(
            value = "update student " +
                    "set group_account_id = ?1 " +
                    "where account_id = ?2",
            nativeQuery = true)
    void acceptJoinGroup(Integer groupId, Integer accountId);

    @Query(
            value = "select *\n" +
                    "from group_account\n" +
                    "where delete_flag = false",
            nativeQuery = true)
    Page<GroupAccount> findAllGroup(Pageable pageable);

    @Query(
            value = "select *\n" +
                    "from group_account\n" +
                    "where `name` like %?1%\n" +
                    "and delete_flag = false",
            nativeQuery = true)
    Page<GroupAccount> findAllByNameContaining(String name, Pageable pageable);

    @Modifying
    @Query(
            value = "update group_account \n" +
                    "set delete_flag = true\n" +
                    "where id = ?1 ",
            nativeQuery = true)
    void deleteGroup(Integer groupId);

    @Query(
            value = "select st.id , st.name, st.date_of_birth as dateOfBirth, st.email, st.gender, st.phone,st.address, gr.name as nameGrade\n" +
                    "from student st\n" +
                    "join grade gr\n" +
                    "on st.grade_id = gr.id\n" +
                    "where group_account_id = ?1 ",
            nativeQuery = true)
    List<StudentInformation> getStudentGroup(Integer groupId);

    @Modifying
    @Query(
            value = "update student\n" +
                    "set student.group_account_id = null\n" +
                    "where student.id = ?1 ",
            nativeQuery = true)
    void deleteStudentGroup(Integer studentId);

    @Modifying
    @Query(
            value = "update group_account \n" +
                    "set status = true\n" +
                    "where id = ?1 ",
            nativeQuery = true)
    void acceptGroup(Integer groupId);


    GroupAccount findByName(String nameGroup);

    @Query(
            value = "select student.status_join as statusJoin , student.group_account_id as groupAccountId\n" +
                    "from student\n" +
                    "where student.id = ?1 ",
            nativeQuery = true)
    CheckJoinGroupDTO checkJoinGroup(Integer accountId);

    @Modifying
    @Query(
            value = "update student\n" +
                    "set student.status_join = true\n" +
                    "where id = ?1 ",
            nativeQuery = true)
    void acceptJoinGroupByAccount(Integer studentId);

    @Modifying
    @Query(
            value = "update student " +
                    "set student.group_account_id = null " +
                    "where id = ?1",
            nativeQuery = true)
    void denyJoinGroupByAccount(Integer studentId);

    @Query(value = "select *from process_manager.group_account",nativeQuery = true)
    List<GroupAccount> findAllGroup();

    @Modifying
    @Query(value = "UPDATE `process_manager`.`group_account` SET `date` = ?1 WHERE (`id` = ?2)",nativeQuery = true)
    void updateDeadline(String date, Integer id);
}


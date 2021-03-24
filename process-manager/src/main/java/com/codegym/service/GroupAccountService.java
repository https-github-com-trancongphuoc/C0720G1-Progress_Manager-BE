package com.codegym.service;


import com.codegym.dto.StudentInformation;
import com.codegym.entity.GroupAccount;
import com.codegym.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GroupAccountService {
    void saveGroup(Integer leaderId,String nameGroup);

    void acceptJoinGroup(Integer groupId, Integer accountId);

    Page<GroupAccount> listGroup(Pageable pageable);

    Page<GroupAccount> searchGroup(String name, Pageable pageable);

    void deleteGroup(Integer groupId,List<Integer> integerList);

    List<StudentInformation> getStudentGroup(Integer groupId);

    void deleteStudentGroup(Integer studentId);

    void acceptGroup(Integer groupId);

    GroupAccount getGroupById(Integer id);

    void createGroup(String nameGroup, List<Student> studentList);
}

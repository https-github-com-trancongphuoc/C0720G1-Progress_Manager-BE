package com.codegym.service;


import com.codegym.dto.StudentInformation;
import com.codegym.entity.GroupAccount;
import com.codegym.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GroupAccountService {
    void saveGroup(String name, Integer leaderId);

    void acceptJoinGroup(Integer groupId, Integer accountId);

    Page<GroupAccount> listGroup(Pageable pageable);

    Page<GroupAccount> searchGroup(String name,Pageable pageable);

    void deleteGroup(Integer groupId);

    List<StudentInformation> getStudentGroup(Integer groupId);

    void deleteStudentGroup(Integer studentId);

    void acceptGroup(Integer groupId);

}
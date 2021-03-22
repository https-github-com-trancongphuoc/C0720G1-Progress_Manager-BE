package com.codegym.service.impl;

import com.codegym.dto.StudentInformation;
import com.codegym.entity.GroupAccount;
import com.codegym.entity.Student;
import com.codegym.entity.GroupAccount;
import com.codegym.repository.GroupAccountRepository;
import com.codegym.repository.StudentRepository;
import com.codegym.service.GroupAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupAccountServiceImpl implements GroupAccountService {

    @Autowired
    private GroupAccountRepository groupAccountRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void saveGroup(String name, Integer leaderId) {
        groupAccountRepository.saveGroup(name);
        groupAccountRepository.saveGroup(leaderId);
    }

    @Override
    public void acceptJoinGroup(Integer groupId, Integer accountId) {
        groupAccountRepository.acceptJoinGroup(groupId, accountId);
    }

    @Override
    public Page<GroupAccount> listGroup(Pageable pageable) {
        return groupAccountRepository.findAllGroup(pageable);
    }

    @Override
    public Page<GroupAccount> searchGroup(String nameSearch, Pageable pageable) {
        return groupAccountRepository.findAllByNameContaining(nameSearch, pageable);
    }

    @Override
    public void deleteGroup(Integer groupId) {
        groupAccountRepository.deleteGroup(groupId);
    }

    @Override
    public List<StudentInformation> getStudentGroup(Integer groupId) {
        return groupAccountRepository.getStudentGroup(groupId);
    }

    @Override
    public void deleteStudentGroup(Integer studentId) {
        groupAccountRepository.deleteStudentGroup(studentId);
    }

    @Override
    public void acceptGroup(Integer groupId) {
        groupAccountRepository.acceptGroup(groupId);
    }

    
    @Override
    public GroupAccount getGroupById(Integer id) {
        return groupAccountRepository.findById(id).orElse(null);
    }
}

package com.codegym.service;

import com.codegym.dto.GroupAccountDTO;
import com.codegym.dto.InfoTopicRegisterDTO;
import com.codegym.dto.TopicProcessDTO;
import com.codegym.entity.InfoTopicRegister;
import com.codegym.entity.Student;
import com.codegym.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface TopicManagerService {

    Page<Topic> findAllByTopic(Pageable pageable);

    Page<Topic> findAllByTopicFind(String name, Pageable pageable);

    Topic findByIdTopic(Integer id);

    InfoTopicRegister findAllByGroupAccount(Integer id);

    List<Student> findAllStudent(Integer id);

    void topicCancel(Integer id);

    void deleteTopic(Integer id);

    void updateDeadline(GroupAccountDTO groupAccountDTO);

    void sendStudent(InfoTopicRegisterDTO infoTopicRegisterDTO) throws MessagingException, UnsupportedEncodingException;

    List<String> listEmailStudent();

    List<String> listEmailTeacher();

    List<String> listEmailStudentFinish();

    List<String> listEmailTeacherFinish();

    List<String> listEmailStudentDeadline();

    void sendStudentDeadline(GroupAccountDTO groupAccountDTO) throws MessagingException, UnsupportedEncodingException;

    void createTopicProcess(TopicProcessDTO topicProcessDTO);

    void statusInfo(Integer teacherId, Integer id);

    List<Integer> listIdStudent();

    List<Integer> listIdTeacher();

    List<Integer> listIdStudentFinish();

    List<Integer> listIdTeacherFinish();

    List<Integer> listIdStudentDeadline();
}

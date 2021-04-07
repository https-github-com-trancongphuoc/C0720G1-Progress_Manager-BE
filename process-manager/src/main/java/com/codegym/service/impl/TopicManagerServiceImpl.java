package com.codegym.service.impl;

import com.codegym.dto.GroupAccountDTO;
import com.codegym.dto.InfoTopicRegisterDTO;
import com.codegym.dto.TopicProcessDTO;
import com.codegym.entity.*;
import com.codegym.repository.*;
import com.codegym.service.TopicManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public class TopicManagerServiceImpl implements TopicManagerService {
    @Autowired
    TopicManagerRepository topicManagerRepository;
    @Autowired
    StudentTopicRepository studentTopicRepository;
    @Autowired
    InfoTopicRegisterRepository infoTopicRegisterRepository;
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    GroupAccountRepository groupAccountRepository;

    @Override
    public Page<Topic> findAllByTopic(Pageable pageable) {
        return topicManagerRepository.findAllByTopic(pageable);
    }

    @Override
    public Page<Topic> findAllByTopicFind(String name, Pageable pageable) {
        return topicManagerRepository.findAllByTopicFind(false, name, pageable);
    }

    @Override
    public Topic findByIdTopic(Integer id) {
        return topicManagerRepository.findByIdTopic(id);
    }

    @Override
    public InfoTopicRegister findAllByGroupAccount(Integer id) {
        return infoTopicRegisterRepository.findAllByGroupAccount(id);
    }

    @Override
    public List<Student> findAllStudent(Integer id) {
        return studentTopicRepository.findAllStudent(id);
    }

    @Override
    public void topicCancel(Integer id) {
        infoTopicRegisterRepository.topicCancel(true, id);
    }

    @Override
    public void deleteTopic(Integer id) {
        topicManagerRepository.deleteTopic(true, id);
    }

    @Override
    public void updateDeadline(GroupAccountDTO groupAccountDTO) {
        groupAccountRepository.updateDeadline(groupAccountDTO.getDate(), groupAccountDTO.getId());
    }

    @Override
    public void sendStudent(InfoTopicRegisterDTO infoTopicRegisterDTO) throws MessagingException, UnsupportedEncodingException {
        String subject = "Thông báo hủy đề tài hiện tại";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        String mailContent;
        String[] emailList = new String[infoTopicRegisterDTO.getStudentList().size()];

        for (int i = 0; i < infoTopicRegisterDTO.getStudentList().size(); i++) {
            emailList[i] = infoTopicRegisterDTO.getStudentList().get(i).getEmail();
        }
        helper.setTo(emailList);
        helper.setFrom("phuoctc.2000", "Process Manager - Quản lí đề tài!");
        helper.setSubject(subject);
        mailContent = "<div style='text-align: center'>\n" +
                "    <h2>Nội dung nguyên nhân hủy đề tài</h2>\n" +
                "    <p><span style='font-weight: bold'>" + infoTopicRegisterDTO.getMessageCancel() + "</span></p>\n" +
                "    <p><span style='font-weight: bold'> Yêu cầu bạn hãy nhanh chóng chọn đề tài mới </span></p>\n" +
                "    </div>";
        helper.setText(mailContent, true);
        javaMailSender.send(message);
    }

    @Override
    public List<String> listEmailStudent() {
        return infoTopicRegisterRepository.listEmailStudent();
    }

    @Override
    public List<String> listEmailTeacher() {
        return infoTopicRegisterRepository.listEmailTeacher();
    }

    @Override
    public List<String> listEmailStudentFinish() {
        return infoTopicRegisterRepository.listEmailStudentFinish();
    }

    @Override
    public List<String> listEmailTeacherFinish() {
        return infoTopicRegisterRepository.listEmailTeacherFinish();
    }

    @Override
    public List<String> listEmailStudentDeadline() {
        return infoTopicRegisterRepository.listEmailStudentDeadline();
    }

    @Override
    public void sendStudentDeadline(GroupAccountDTO groupAccountDTO) throws MessagingException, UnsupportedEncodingException {
        String subject = "Thông báo hạn chót nộp thông tin dự án sẽ thực hiện";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        String mailContent;
        String[] emailList = new String[groupAccountDTO.getStudentList().size()];

        for (int i = 0; i < groupAccountDTO.getStudentList().size(); i++) {
            emailList[i] = groupAccountDTO.getStudentList().get(i).getEmail();
        }
        helper.setTo(emailList);
        helper.setFrom("phuoctc.2000", "Process Manager - Quản lí đề tài!");
        helper.setSubject(subject);
        mailContent = "<div style='text-align: center'>\n" +
                "    <h2>Nội dung thông báo hạn chót nộp dự án</h2>\n" +
                "    <p><span style='font-weight: bold'> Yêu cầu nhóm trưởng và thành viên nộp thông tin sơ lược về dự án sẽ thực hiện trước ngày " + groupAccountDTO.getDate() + " </span></p>\n" +
                "    <p><span style='font-weight: bold'> Nếu không nộp trước thời hạn trên thì sẽ không cho phép tốt nghiệp!</span></p>\n" +
                "    </div>";
        helper.setText(mailContent, true);
        javaMailSender.send(message);
    }

    @Override
    public void createTopicProcess(TopicProcessDTO topicProcessDTO) {
        topicManagerRepository.createTopicProcess(topicProcessDTO.getDateEnd(), topicProcessDTO.getDateStart(), 0, topicProcessDTO.getProcessNumber(), false, topicProcessDTO.getInfoTopicRegister());
    }

    @Override
    public void statusInfo(Integer teacherId,Integer id) {
        topicManagerRepository.statusInfo(true, teacherId,id);
    }

    @Override
    public List<Integer> listIdStudent() {
        return infoTopicRegisterRepository.listIdStudent();
    }

    @Override
    public List<Integer> listIdTeacher() {
        return infoTopicRegisterRepository.listIdTeacher();
    }

    @Override
    public List<Integer> listIdStudentFinish() {
        return infoTopicRegisterRepository.listIdStudentFinish();
    }

    @Override
    public List<Integer> listIdTeacherFinish() {
        return infoTopicRegisterRepository.listIdTeacherFinish();
    }

    @Override
    public List<Integer> listIdStudentDeadline() {
        return infoTopicRegisterRepository.listIdStudentDeadline();
    }
}

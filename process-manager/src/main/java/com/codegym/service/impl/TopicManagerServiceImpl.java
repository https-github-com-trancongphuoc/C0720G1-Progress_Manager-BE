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
        String subject = "Th??ng b??o h???y ????? t??i hi???n t???i";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        String mailContent;
        String[] emailList = new String[infoTopicRegisterDTO.getStudentList().size()];

        for (int i = 0; i < infoTopicRegisterDTO.getStudentList().size(); i++) {
            emailList[i] = infoTopicRegisterDTO.getStudentList().get(i).getEmail();
        }
        helper.setTo(emailList);
        helper.setFrom("phuoctc.2000", "Process Manager - Qu???n l?? ????? t??i!");
        helper.setSubject(subject);
        mailContent = "<div style='text-align: center'>\n" +
                "    <h2>N???i dung nguy??n nh??n h???y ????? t??i</h2>\n" +
                "    <p><span style='font-weight: bold'>" + infoTopicRegisterDTO.getMessageCancel() + "</span></p>\n" +
                "    <p><span style='font-weight: bold'> Y??u c???u b???n h??y nhanh ch??ng ch???n ????? t??i m???i </span></p>\n" +
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
        String subject = "Th??ng b??o h???n ch??t n???p th??ng tin d??? ??n s??? th???c hi???n";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        String mailContent;
        String[] emailList = new String[groupAccountDTO.getStudentList().size()];

        for (int i = 0; i < groupAccountDTO.getStudentList().size(); i++) {
            emailList[i] = groupAccountDTO.getStudentList().get(i).getEmail();
        }
        helper.setTo(emailList);
        helper.setFrom("phuoctc.2000", "Process Manager - Qu???n l?? ????? t??i!");
        helper.setSubject(subject);
        mailContent = "<div style='text-align: center'>\n" +
                "    <h2>N???i dung th??ng b??o h???n ch??t n???p d??? ??n</h2>\n" +
                "    <p><span style='font-weight: bold'> Y??u c???u nh??m tr?????ng v?? th??nh vi??n n???p th??ng tin s?? l?????c v??? d??? ??n s??? th???c hi???n tr?????c ng??y " + groupAccountDTO.getDate() + " </span></p>\n" +
                "    <p><span style='font-weight: bold'> N???u kh??ng n???p tr?????c th???i h???n tr??n th?? s??? kh??ng cho ph??p t???t nghi???p!</span></p>\n" +
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

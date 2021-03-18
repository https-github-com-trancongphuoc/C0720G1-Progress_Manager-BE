package com.codegym.service.impl;

import com.codegym.dto.AppreciateDTO;
import com.codegym.entity.Student;
import com.codegym.service.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
public class SendMailImpl implements SendMail {

    @Autowired
    private JavaMailSender javaMailSender;

    public void Appreciate(AppreciateDTO appreciateDTO) throws MessagingException, UnsupportedEncodingException {
        String subject = "Đánh giá của giáo viên về quá trình làm đề tài";
        String mailContent;

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");

        String[] emailList = new String[appreciateDTO.getStudentList().size()];

        for (int i = 0; i < appreciateDTO.getStudentList().size(); i++) {
            emailList[i] = appreciateDTO.getStudentList().get(i).getEmail();
        }

        helper.setTo(emailList);
        helper.setFrom("phuoctc.2000","Process Manager - Quản lí đề tài!");
        helper.setSubject(subject);
        mailContent = "<div style='text-align: center'>\n" +
                "    <h2>Đánh giá của giáo viên</h2>\n" +
                "    <p>Nội dung đánh giá: <span style='font-weight: bold'>"+ appreciateDTO.getContent() +"</span></p>\n" +
                "    <p>Phần trăm tiến độ: <span style='font-weight: bold'>"+ appreciateDTO.getPercentProcess() +"</span></p>\n" +
                "    <a href='http://localhost:4200/process-detail/"+appreciateDTO.getIdProcessDetail()+"'>Xem chi tiết</a>\n" +
                "</div>";
        helper.setText(mailContent, true);
        javaMailSender.send(message);
    }
}

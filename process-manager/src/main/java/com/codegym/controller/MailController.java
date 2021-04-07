package com.codegym.controller;

import com.codegym.dto.NotificationDTO;
import com.codegym.service.CommentPostService;
import com.codegym.service.TopicManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@EnableScheduling
public class MailController {
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    TopicManagerService topicManagerService;
    @Autowired
    CommentPostService commentPostService;

    @Scheduled(cron = "0 22 02 * * ?")
    public void sendEmailDeadline() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dayPlusAWeek = LocalDate.now().plusDays(1);
        String day = formatter.format(dayPlusAWeek);

        List<String> listEmailTeacher = topicManagerService.listEmailTeacher();
        List<String> listEmailStudent = topicManagerService.listEmailStudent();
        Set<String> listEmail = new HashSet<>();
        listEmail.addAll(listEmailStudent);
        listEmail.addAll(listEmailTeacher);

        if (!(listEmail.size() == 0)) {
            String[] array = listEmail.toArray(new String[0]);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("phuoctc.2000");
            message.setTo(array);
            message.setSubject("Nhắc nhở hạn chót nộp báo cáo kết thúc giai đoạn");
            message.setText("Chào bạn \n"
                    + "Bạn đang có một hạn chót nộp báo cáo vào ngày " + day);
            this.emailSender.send(message);
        }
    }

    @Scheduled(cron = "0 22 02 * * ?")
    public void sendEmailFinish() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dayPlusAWeek = LocalDate.now().plusDays(1);
        String dayEnd = formatter.format(dayPlusAWeek);

        LocalDate dayPlusAWeeks = LocalDate.now().plusDays(2);
        String dayStart = formatter.format(dayPlusAWeeks);
        List<String> listEmailTeacher = topicManagerService.listEmailTeacherFinish();
        List<String> listEmailStudent = topicManagerService.listEmailStudentFinish();
        Set<String> listEmail = new HashSet<>();
        listEmail.addAll(listEmailStudent);
        listEmail.addAll(listEmailTeacher);

        if (!(listEmail.size() == 0)) {
            String[] array = listEmail.toArray(new String[0]);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("phuoctc.2000");
            message.setTo(array);
            message.setSubject("Nhắc nhở kết thúc giai đoạn");
            message.setText("Chào bạn \n"
                    + "\nBạn đã kết thúc giai đoạn hiện tại vào ngày " + dayEnd
                    + "\nBạn sẽ tiếp tục giai đoạn tiếp theo vào ngày " + dayStart);
            this.emailSender.send(message);
        }
    }

    @Scheduled(cron = "0 22 02 * * ?")
    public void sendEmailDeadlineTopic() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dayPlusAWeek = LocalDate.now().plusDays(1);
        String day = formatter.format(dayPlusAWeek);

        List<String> listEmailStudent = topicManagerService.listEmailStudentDeadline();
        Set<String> listEmail = new HashSet<>(listEmailStudent);
        if (!(listEmail.size() == 0)) {
            String[] array = listEmail.toArray(new String[0]);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("phuoctc.2000");
            message.setTo(array);
            message.setSubject("Nhắc nhở hạn chót nộp thông tin sơ bộ về dự án");
            message.setText("Chào bạn \n"
                    + "Bạn đang có một hạn chót nộp thông tin sơ bộ về dự án vào ngày " + day);
            this.emailSender.send(message);
        }
    }

    @Scheduled(cron = "0 22 02 * * ?")
    public void sendIdDeadline() {
        List<Integer> listIdTeacher = topicManagerService.listIdTeacher();
        List<Integer> listIdStudent = topicManagerService.listIdStudent();
        Set<Integer> listId = new HashSet<>();
        listId.addAll(listIdTeacher);
        listId.addAll(listIdStudent);
        if (!(listId.size() == 0)) {
            Integer[] arrayId = listId.toArray(new Integer[0]);
            for (int i = 0; i < arrayId.length; i++) {
                NotificationDTO notificationDTO = new NotificationDTO();
                notificationDTO.setContent("Bạn có một hạn chót báo cáo kết thúc dự án!");
                notificationDTO.setTitle("Bạn có tin nhắn");
                notificationDTO.setUrl("http://mail.google.com");
                notificationDTO.setAccountId(arrayId[i]);
                commentPostService.createNotificationList(notificationDTO);
            }
        }
    }

    @Scheduled(cron = "0 22 02 * * ?")
    public void sendIdDeadFinish() {
        List<Integer> listIdTeacher = topicManagerService.listIdTeacherFinish();
        List<Integer> listIdStudent = topicManagerService.listIdStudentFinish();
        Set<Integer> listId = new HashSet<>();
        listId.addAll(listIdTeacher);
        listId.addAll(listIdStudent);
        if (!(listId.size() == 0)) {
            Integer[] arrayId = listId.toArray(new Integer[0]);
            for (int i = 0; i < arrayId.length; i++) {
                NotificationDTO notificationDTO = new NotificationDTO();
                notificationDTO.setContent("Bạn đã hoàn thành một giai đoạn của dự án dự án!");
                notificationDTO.setTitle("Bạn có tin nhắn");
                notificationDTO.setUrl("http://mail.google.com");
                notificationDTO.setAccountId(arrayId[i]);
                commentPostService.createNotificationList(notificationDTO);
            }
        }
    }

    @Scheduled(cron = "0 22 02 * * ?")
    public void sendIdDeadlineTopic() {
        List<Integer> listIdStudent = topicManagerService.listIdStudentDeadline();
        Set<Integer> listId = new HashSet<>(listIdStudent);
        if (!(listId.size() == 0)) {
            Integer[] arrayId = listId.toArray(new Integer[0]);
            for (int i = 0; i < arrayId.length; i++) {
                NotificationDTO notificationDTO = new NotificationDTO();
                notificationDTO.setContent("Bạn có một hạn chót gửi thông tin dự án sẽ làm!");
                notificationDTO.setTitle("Bạn có tin nhắn");
                notificationDTO.setUrl("http://mail.google.com");
                notificationDTO.setAccountId(arrayId[i]);
                commentPostService.createNotificationList(notificationDTO);
            }
        }
    }
}

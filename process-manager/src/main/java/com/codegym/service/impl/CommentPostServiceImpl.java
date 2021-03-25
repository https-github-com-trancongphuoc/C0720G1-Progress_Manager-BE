package com.codegym.service.impl;

import com.codegym.dto.CommentPostDTO;
import com.codegym.dto.NotificationDTO;

import com.codegym.entity.Comment;
import com.codegym.entity.Report;
import com.codegym.repository.CommentPostRepository;
import com.codegym.repository.NotificationManagerRepository;
import com.codegym.repository.ReportPostRepository;
import com.codegym.service.CommentPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

@Service
public class CommentPostServiceImpl implements CommentPostService {

    @Autowired
    CommentPostRepository commentPostRepository;

    @Autowired
    ReportPostRepository reportPostRepository;

    @Autowired
    NotificationManagerRepository notificationManagerRepository;

    @Autowired
    JavaMailSender javaMailSender;
    String edit = "(Đã chỉnh sửa)";
    @Override
    public Comment findByIdComment(Integer id) {
        return commentPostRepository.findByIdComment(id);
    }

    @Override
    public void createCommentPost(CommentPostDTO commentPostDTO) {
        commentPostRepository.createCommentPost(commentPostDTO.getContent(), LocalDateTime.now().toString(), commentPostDTO.getAccountId(), false, false, commentPostDTO.getTitle(), commentPostDTO.getTopicProcessId());
    }

    @Override
    public void updateCommentPost(CommentPostDTO commentPostDTO) {
        commentPostRepository.updateCommentPost(commentPostDTO.getTitle(), commentPostDTO.getContent() + edit, commentPostDTO.getId());
    }

    @Override
    public void createReplyComment(CommentPostDTO commentPostDTO) {
        commentPostRepository.createReplyComment(commentPostDTO.getContent(), LocalDateTime.now().toString(), commentPostDTO.getAccountId(), false, false, commentPostDTO.getReplyCommentId());
    }

    @Override
    public void updateComment(CommentPostDTO commentPostDTO) {
        commentPostRepository.updateComment(commentPostDTO.getContent(), commentPostDTO.getId());
    }

    @Override
    public Page<Comment> findAllByComment(Integer id, Pageable pageable) {
        return commentPostRepository.findAllByComment(id, pageable);
    }

    @Override
    public Page<Comment> findAllByCommentReply(Integer id, Pageable pageable) {
        return commentPostRepository.findAllByCommentReply(id, pageable);
    }

    @Override
    public Page<Report> findAllByReport(Integer id, Pageable pageable) {
        return reportPostRepository.findAllByReport(id, pageable);
    }

    @Override
    public void deleteComment(Integer id, Integer accountId) {
        commentPostRepository.deleteComment(true, id, accountId);
    }

    @Override
    public void deleteComment(Integer id) {
        commentPostRepository.deleteComment(true, id);
    }

    @Override
    public void questionStudent(CommentPostDTO commentPostDTO) throws MessagingException, UnsupportedEncodingException {
        String subject = "Câu hỏi của sinh viên về quá trình làm đề tài";
        String mailContent;

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        String email ;
        email = commentPostDTO.getEmail();
        System.out.println(email);
        helper.setTo(email);

        helper.setFrom("phuoctc.2000", "Process Manager - Quản lí đề tài!");
        helper.setSubject(subject);
        mailContent = "<div style='text-align: center'>\n" +
                "    <h2>Câu hỏi của sinh viên</h2>\n" +
                "    <p>Tiêu đề câu hỏi dành cho giảng viên: <span style='font-weight: bold'>" + commentPostDTO.getTitle() + "</span></p>\n" +
                "    <p>Nội dung câu hỏi dành cho giảng viên: <span style='font-weight: bold'>" + commentPostDTO.getContent() + "</span></p>\n" +
                "    <a href='http://localhost:4200/process-detail/" + commentPostDTO.getProcessDetailId() + "'>Xem chi tiết</a>\n" +
                "</div>";
        helper.setText(mailContent, true);
        javaMailSender.send(message);
    }

    @Override
    public void createNotification(NotificationDTO notificationDTO) {
        notificationManagerRepository.createNotification(notificationDTO.getContent(), false, LocalDateTime.now().toString(), notificationDTO.getTitle(), notificationDTO.getAccountId(), notificationDTO.getAccountSendNotificationId());
    }

}

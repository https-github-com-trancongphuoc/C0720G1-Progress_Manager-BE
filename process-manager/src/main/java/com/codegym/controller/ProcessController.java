package com.codegym.controller;

import com.codegym.dto.AppreciateDTO;
import com.codegym.dto.CommentDTO;
import com.codegym.entity.Comment;
import com.codegym.entity.InfoTopicRegister;
import com.codegym.entity.Notification;
import com.codegym.entity.TopicProcess;
import com.codegym.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class ProcessController {

    @Autowired
    private TopicProcessService topicProcessService;

    @Autowired
    private InfoTopicRegisterService infoTopicRegisterService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private SendMail sendMail;

    @GetMapping("/process-list")
    private ResponseEntity<?> getListProcess() {
        List<InfoTopicRegister> processList = infoTopicRegisterService.getListProcess();

        return new ResponseEntity<>(processList, HttpStatus.OK);
    }


    @GetMapping("/process-detail/{id}")
    private ResponseEntity<?> getProcessDetail(@PathVariable Integer id) {
        InfoTopicRegister processDetail = infoTopicRegisterService.getProcessDetailById(id);

        return new ResponseEntity<>(processDetail, HttpStatus.OK);
    }


    @GetMapping("/appreciate-list/{id}")
    private ResponseEntity<?> getListAppreciate(@PathVariable Integer id,
                                                @PageableDefault(size = 3) Pageable pageable) {
        Page<Comment> commentList = commentService.getListAppreciate(id, pageable);

        List<CommentDTO> commentDTOList = new ArrayList<>();

        for (Comment comment : commentList) {
            CommentDTO commentDTO = new CommentDTO(comment);

            commentDTO.setReplyCommentList(commentService.getListRepComment(comment.getId()));

            commentDTOList.add(commentDTO);
        }

        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    }


    @GetMapping("/rep-comment-list/{id}")
    private ResponseEntity<?> getRepCommentList(@PathVariable Integer id) {
        List<Comment> commentList = commentService.getListRepComment(id);

        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }



    @PostMapping("/appreciate")
    private ResponseEntity<?> teacherAppreciate(@RequestBody AppreciateDTO appreciateDTO) throws UnsupportedEncodingException, MessagingException {


        TopicProcess topicProcess = topicProcessService.getProcessById(appreciateDTO.getIdProcess());

        // Update lại tiến độ của giai đoạn
        topicProcess.setPercentProcess(appreciateDTO.getPercentProcess());
        if (topicProcess.getPercentProcess() == 100) {
            topicProcess.setStatus(true);
        }
        topicProcess = topicProcessService.updateProcess(topicProcess);
        // ------

        // Lưu đánh giá của giáo viên vào DB
        Comment comment = new Comment();
        comment.setTimeComment(LocalDateTime.now().toString());
        comment.setAccount(accountService.getAccountById(appreciateDTO.getIdAccount()));
        comment.setContent(appreciateDTO.getContent());
        comment.setDeleteFlag(false);
        comment.setTopicProcess(topicProcess);
        comment.setStatus(true);
        commentService.teacherAppreciate(comment);


        // Thông báo đánh giá của giáo viên đến sinh viên
        for (int i = 0; i < appreciateDTO.getStudentList().size(); i++) {
            Notification notification = new Notification();
            notification.setTitle("Giáo viên vừa đánh giá quá trình làm đề tài của bạn");
            notification.setContent(appreciateDTO.getContent());
            notification.setTimeNotification(LocalDateTime.now().toString());
            notification.setAccount(accountService.getAccountByIdStudent(appreciateDTO.getStudentList().get(i).getId()));
            notification.setStatus(false);
            notification.setAccountSendNotification(accountService.getAccountById(appreciateDTO.getIdAccount()));
            notificationService.save(notification);
        }

        sendMail.Appreciate(appreciateDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/process-by-group/{id}")
    private ResponseEntity<?> getProcessDetailByGroupId(@PathVariable Integer id) {
        InfoTopicRegister infoTopicRegister = infoTopicRegisterService.getProcessDetailByGroupId(id);

        return new ResponseEntity<>(infoTopicRegister, HttpStatus.OK);
    }


}

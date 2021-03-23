package com.codegym.controller;

import com.codegym.dto.AppreciateDTO;
import com.codegym.dto.CommentDTO;
import com.codegym.entity.*;
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
import java.time.LocalDate;
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

    @Autowired
    private GroupAccountService groupAccountService;

    @Autowired
    private TopicService topicService;

    @GetMapping("/process-list")
    private ResponseEntity<?> getListProcess(@PageableDefault(size = 5) Pageable pageable) {
        Page<InfoTopicRegister> processList = infoTopicRegisterService.getListProcess(pageable);

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


        InfoTopicRegister infoTopicRegister = infoTopicRegisterService.getProcessDetailById(appreciateDTO.getIdProcessDetail());
        if (infoTopicRegister.getProcessList().get(infoTopicRegister.getProcessList().size() - 1).getPercentProcess() == 100) {
            infoTopicRegister.setStatusComplete(true);
        }
        infoTopicRegisterService.registerInfoTopic(infoTopicRegister);





        // Lưu đánh giá của giáo viên vào DB
        Comment comment = new Comment();
        comment.setTimeComment(LocalDateTime.now().toString());
        comment.setAccount(accountService.getAccountById(appreciateDTO.getIdAccount()));
        comment.setContent(appreciateDTO.getContent());
        comment.setDeleteFlag(false);
        comment.setTopicProcess(topicProcess);
        comment.setStatus(true);
        comment = commentService.teacherAppreciate(comment);


        // Thông báo đánh giá của giáo viên đến sinh viên
        for (int i = 0; i < appreciateDTO.getStudentList().size(); i++) {
            Notification notification = new Notification();
            notification.setTitle(accountService.getAccountById(appreciateDTO.getIdAccount()).getTeacher().getName() + " vừa đánh giá đề tài của bạn");
            notification.setContent("Nội dung: " + appreciateDTO.getContent());
            notification.setTimeNotification(LocalDateTime.now().toString());
            notification.setAccount(accountService.getAccountByIdStudent(appreciateDTO.getStudentList().get(i).getId()));
            notification.setStatus(false);
            notification.setAccountSendNotification(accountService.getAccountById(appreciateDTO.getIdAccount()));
            notificationService.save(notification);
        }

        sendMail.Appreciate(appreciateDTO);

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping("/process-by-group/{id}")
    private ResponseEntity<?> getProcessDetailByGroupId(@PathVariable Integer id) {
        InfoTopicRegister infoTopicRegister = infoTopicRegisterService.getProcessDetailByGroupId(id);

        return new ResponseEntity<>(infoTopicRegister, HttpStatus.OK);
    }


    @PostMapping("/edit-appreciate")
    private ResponseEntity<?> editAppreciate(@RequestBody Comment comment) {
        commentService.editAppreciate(comment);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/delete-appreciate")
    private ResponseEntity<?> deleteAppreciate(@RequestBody Comment comment) {
        comment.setDeleteFlag(true);
        commentService.deleteAppreciate(comment);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/reply-appreciate")
    private ResponseEntity<?> replyAppreciate(@RequestBody Comment comment) {
        comment.setDeleteFlag(false);
        comment.setStatus(true);
//        comment.setTitle();
        comment.setTimeComment(LocalDateTime.now().toString());
        comment = commentService.editAppreciate(comment);

        if (!comment.getReplyComment().getAccount().getId().equals(comment.getAccount().getId())) {
            Notification notification = new Notification();
            notification.setAccount(comment.getReplyComment().getAccount());
            notification.setAccountSendNotification(comment.getAccount());
            notification.setStatus(false);
            if (notification.getAccountSendNotification().getStudent() != null) {
                notification.setTitle(notification.getAccountSendNotification().getStudent().getName() + " vừa trả lời đánh giá của bạn");
                notification.setContent("Nội dung: " + comment.getContent());
            } else {
                notification.setTitle(notification.getAccountSendNotification().getTeacher().getName() + " vừa trả lời đánh giá của bạn");
                notification.setContent("Nội dung: " + comment.getContent());
            }
            notification.setTimeNotification(LocalDateTime.now().toString());

            notificationService.save(notification);
        }



        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @GetMapping("/group/{id}")
    private ResponseEntity<?> getMemberInGroup(@PathVariable Integer id) {
        GroupAccount groupAccount = groupAccountService.getGroupById(id);

        return new ResponseEntity<>(groupAccount, HttpStatus.OK);
    }

    @PostMapping("/register-topic")
    private ResponseEntity<?> registerTopic(@RequestBody InfoTopicRegister infoTopicRegister) {
        if (infoTopicRegister.getTopic().getId() == null) {
            infoTopicRegister.setTopic(topicService.registerTopic(infoTopicRegister.getTopic()));
        }

        infoTopicRegister.setStatus(false);
        infoTopicRegister.setStatusComplete(false);

        infoTopicRegisterService.registerInfoTopic(infoTopicRegister);


        return  new ResponseEntity<>(infoTopicRegister, HttpStatus.OK);
    }

    @GetMapping("/get-topic-not-approval")
    private ResponseEntity<?> getListTopicNotApproval(@PageableDefault(size = 5) Pageable pageable,
                                                      @RequestParam Integer id) {
        Page<InfoTopicRegister> infoTopicRegisterList = infoTopicRegisterService.getListTopicNotApproval(id, pageable);

        return new ResponseEntity<>(infoTopicRegisterList, HttpStatus.OK);
    }

    @PostMapping("/approval")
    private ResponseEntity<?> approvalTopic(@RequestBody InfoTopicRegister infoTopicRegister) {
        infoTopicRegister.setStatus(true);
        infoTopicRegisterService.registerInfoTopic(infoTopicRegister);

        for (int i = 0; i < 4; i++) {
            TopicProcess topicProcess = new TopicProcess();
            topicProcess.setInfoTopicRegister(infoTopicRegister);
            topicProcess.setStatus(false);
            topicProcess.setPercentProcess(0);
            topicProcess.setProcessNumber(i + 1);
            topicProcess.setDateStart(LocalDate.now().plusDays(i * 7).toString());
            topicProcess.setDateEnd(LocalDate.now().plusDays((i + 1) * 7).toString());
            topicProcessService.updateProcess(topicProcess);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

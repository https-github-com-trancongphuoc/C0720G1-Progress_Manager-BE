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


    /**
     * PhuocTC: Hiển thị danh sách các đề tài học viên đang làm
     * */
    @GetMapping("/process-list")
    private ResponseEntity<?> getListProcess(@PageableDefault(size = 5) Pageable pageable) {
        Page<InfoTopicRegister> processList = infoTopicRegisterService.getListProcess(pageable);

        if (processList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(processList, HttpStatus.OK);
    }


    /**
     * PhuocTC: Xem chi tiết một đề tài đang làm của học viên
     * */
    @GetMapping("/process-detail/{id}")
    private ResponseEntity<?> getProcessDetail(@PathVariable Integer id) {
        InfoTopicRegister processDetail = infoTopicRegisterService.getProcessDetailById(id);

        if (processDetail == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(processDetail, HttpStatus.OK);
    }


    /**
     * PhuocTC: Hiển thị đánh giá của giáo viên
     * */
    @GetMapping("/appreciate-list")
    private ResponseEntity<?> getListAppreciate(@RequestParam Integer idProcessDetail,
                                                @PageableDefault(size = 3) Pageable pageable) {
        Page<Comment> commentList = commentService.getListAppreciate(idProcessDetail, pageable);

//        if (commentList.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }

        List<CommentDTO> commentDTOList = new ArrayList<>();

        for (Comment comment : commentList) {
            CommentDTO commentDTO = new CommentDTO(comment);
            commentDTO.setReplyCommentList(commentService.getListRepComment(comment.getId()));
            commentDTOList.add(commentDTO);
        }

        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
    }


    /**
     * PhuocTC: Lấy danh sách phản hồi đánh giá
     * */
    @GetMapping("/rep-comment-list/{id}")
    private ResponseEntity<?> getRepCommentList(@PathVariable Integer id) {
        List<Comment> commentList = commentService.getListRepComment(id);

        if (commentList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(commentList, HttpStatus.OK);
    }


    /**
     * PhuocTC: Giáo viên đánh giá tiến độ làm đề tài của học viên
     * */
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
            infoTopicRegisterService.registerInfoTopic(infoTopicRegister);
        }



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
            notification.setUrl("/process-detail/" + appreciateDTO.getIdProcessDetail());
            notification.setStatus(false);
            notification.setAccountSendNotification(accountService.getAccountById(appreciateDTO.getIdAccount()));
            notificationService.save(notification);
        }

        sendMail.Appreciate(appreciateDTO);

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    /**
     * PhuocTC: Lấy thông tin chi tiết đăng ký tiến độ theo nhóm
     * */
    @GetMapping("/process-by-group/{id}")
    private ResponseEntity<?> getProcessDetailByGroupId(@PathVariable Integer id) {
        InfoTopicRegister infoTopicRegister = infoTopicRegisterService.getProcessDetailByGroupId(id);

        if (infoTopicRegister == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(infoTopicRegister, HttpStatus.OK);
    }

    /**
     * PhuocTC: Chỉnh sửa đánh giá
     * */
    @PostMapping("/edit-appreciate")
    private ResponseEntity<?> editAppreciate(@RequestBody Comment comment) {
        commentService.editAppreciate(comment);

        return new ResponseEntity<>(HttpStatus.OK);
    }

//    /**
//     * PhuocTC: Xóa đánh giá
//     * */
//    @PostMapping("/delete-appreciate")
//    private ResponseEntity<?> deleteAppreciate(@RequestBody Comment comment) {
//        comment.setDeleteFlag(true);
//        commentService.deleteAppreciate(comment);
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    /**
     * PhuocTC: Xóa đánh giá
     * */
    @GetMapping("/delete-appreciate/{id}")
    private ResponseEntity<?> deleteAppreciate(@PathVariable Integer id) {
        Comment comment = commentService.getCommentById(id);
        comment.setDeleteFlag(true);
        commentService.deleteAppreciate(comment);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    /**
     * PhuocTC: Phản hồi đánh giá
     * */
    @PostMapping("/reply-appreciate/{id}")
    private ResponseEntity<?> replyAppreciate(@RequestBody Comment comment,
                                              @PathVariable Integer id) {
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
            notification.setUrl("/process-detail/" + id);
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


    /**
     * PhuocTC: Tìm kiếm group theo id
     * */
    @GetMapping("/group/{id}")
    private ResponseEntity<?> getMemberInGroup(@PathVariable Integer id) {
        GroupAccount groupAccount = groupAccountService.getGroupById(id);

        if (groupAccount == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(groupAccount, HttpStatus.OK);
    }

    /**
     * PhuocTC: Đăng ký 1 đề tài
     * */
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

    /**
     * PhuocTC: Lấy danh sách đăng ký đề tài chưa được phê duyệt
     * */
    @GetMapping("/get-topic-not-approval")
    private ResponseEntity<?> getListTopicNotApproval(@PageableDefault(size = 5) Pageable pageable,
                                                      @RequestParam Integer id) {
        Page<InfoTopicRegister> infoTopicRegisterList = infoTopicRegisterService.getListTopicNotApproval(id, pageable);

        if (infoTopicRegisterList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(infoTopicRegisterList, HttpStatus.OK);
    }

    /**
     * PhuocTC: Phê duyệt đề tài
     * */
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

    /**
     * PhuocTC: Lấy danh sách đề tài theo khoa
     * */
    @GetMapping("/get-topic-list/{id}")
    private ResponseEntity<?> getListTopicByFacultyId(@PathVariable Integer id) {
        List<Topic> topicList = topicService.getListTopicByFacultyId(id);

        if (topicList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(topicList, HttpStatus.OK);
    }
}

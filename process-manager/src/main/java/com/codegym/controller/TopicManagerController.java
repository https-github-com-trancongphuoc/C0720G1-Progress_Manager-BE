package com.codegym.controller;

import com.codegym.dto.*;
import com.codegym.entity.*;
import com.codegym.service.CommentPostService;
import com.codegym.service.TopicManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("/api/public/topic-manager")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TopicManagerController {

    @Autowired
    TopicManagerService topicManagerService;
    @Autowired
    CommentPostService commentPostService;

    /**
     * TrungTQ Code: Dùng để hiển thị danh sách đồ án tốt nghiệp
     */
    @RequestMapping(value = "/topic", method = RequestMethod.GET, params = {"page", "size"})
    public ResponseEntity<Page<Topic>> pageTopic(@RequestParam("page") int page,
                                                 @RequestParam("size") int size) {
        Page<Topic> topics = topicManagerService.findAllByTopic(PageRequest.of(page, size));
        if (topics.isEmpty()) {
            return new ResponseEntity<Page<Topic>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<Topic>>(topics, HttpStatus.OK);
    }

    @RequestMapping(value = "/topic-search", method = RequestMethod.GET, params = {"page", "size"})
    public ResponseEntity<Page<Topic>> pageTopicFind(@RequestParam(defaultValue = "") String name,
                                                     @RequestParam("page") int page,
                                                     @RequestParam("size") int size) {
        Page<Topic> topics = topicManagerService.findAllByTopicFind(name, PageRequest.of(page, size));
        if (topics.isEmpty()) {
            return new ResponseEntity<Page<Topic>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<Topic>>(topics, HttpStatus.OK);
    }

    /**
     * TrungTQ Code: Dùng để lấy thông tin dự án ở id=?
     */
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Topic> findTopicById(@PathVariable("id") Integer id) {
        Topic topic = topicManagerService.findByIdTopic(id);
        if (topic == null) {
            return new ResponseEntity<Topic>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Topic>(topic, HttpStatus.OK);
    }

    /**
     * TrungTQ Code: Dùng để lấy thông tin nhóm dự án ở id=?
     */
    @RequestMapping(value = "/findByGroup/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<InfoTopicRegister> findInfoRegisterById(@PathVariable("id") Integer id) {
        InfoTopicRegister groupAccount = topicManagerService.findAllByGroupAccount(id);
        if (groupAccount == null) {
            return new ResponseEntity<InfoTopicRegister>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<InfoTopicRegister>(groupAccount, HttpStatus.OK);
    }

    @RequestMapping(value = "/findByStudent/{id}/list-student", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> listIdGroup(@PathVariable("id") Integer id) {
        List<Student> student = topicManagerService.findAllStudent(id);
        if (student.isEmpty()) {
            return new ResponseEntity<List<Student>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Student>>(student, HttpStatus.OK);
    }

    @RequestMapping(value = "/cancel-topic", method = RequestMethod.POST)
    public ResponseEntity<Void> deleteTopic(@Valid @RequestBody InfoTopicRegisterDTO infoTopicRegisterDTO, UriComponentsBuilder ucBuilder) throws UnsupportedEncodingException, MessagingException {
        topicManagerService.sendStudent(infoTopicRegisterDTO);
        topicManagerService.deleteTopic(infoTopicRegisterDTO.getTopicId());
        topicManagerService.topicCancel(infoTopicRegisterDTO.getId());
        for (int i = 0; i < infoTopicRegisterDTO.getStudentList().size(); i++) {
            NotificationDTO notificationDTO = new NotificationDTO();
            String content = "Nội dung hủy: ";
            String title = "Thông báo hủy đề tài";
            notificationDTO.setTitle(title);
            notificationDTO.setContent(content + infoTopicRegisterDTO.getMessageCancel());
            notificationDTO.setUrl("/group/" + infoTopicRegisterDTO.getId());
            notificationDTO.setAccountId(infoTopicRegisterDTO.getStudentList().get(i).getId());
            notificationDTO.setAccountSendNotificationId(infoTopicRegisterDTO.getTeacher().getId());
            commentPostService.createNotificationUrl(notificationDTO);
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update-deadline", method = RequestMethod.POST)
    public ResponseEntity<Void> deadline(@Valid @RequestBody GroupAccountDTO groupAccountDTO, UriComponentsBuilder ucBuilder) throws UnsupportedEncodingException, MessagingException {
        topicManagerService.sendStudentDeadline(groupAccountDTO);
        topicManagerService.updateDeadline(groupAccountDTO);
        for (int i = 0; i < groupAccountDTO.getStudentList().size(); i++) {
            NotificationDTO notificationDTO = new NotificationDTO();
            String content = "Hạn chót nộp đề tài dự án ";
            String title = "Thông báo hạn chót nộp đề tài";
            notificationDTO.setContent(content + groupAccountDTO.getDate());
            notificationDTO.setTitle(title);
            notificationDTO.setUrl("/group/" + groupAccountDTO.getId());
            notificationDTO.setAccountId(groupAccountDTO.getStudentList().get(i).getId());
            notificationDTO.setAccountSendNotificationId(groupAccountDTO.getTeacherId());
            commentPostService.createNotificationUrl(notificationDTO);
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

//    @RequestMapping(value = "/create-process", method = RequestMethod.POST)
//    public ResponseEntity<Void> createStudent(@Valid @RequestBody List<TopicProcessDTO> topicProcessDTOS, UriComponentsBuilder ucBuilder) {
//        topicManagerService.statusInfo(topicProcessDTOS.get(0).getInfoTopicRegister());
//        for (TopicProcessDTO topicProcessDTO : topicProcessDTOS){
//            topicManagerService.createTopicProcess(topicProcessDTO);
//        }return new ResponseEntity<Void>(HttpStatus.CREATED);
//    }

    @RequestMapping(value = "/create-process", method = RequestMethod.POST)
    public ResponseEntity<Void> createStudent(@Valid @RequestBody InfoTopicRegisterDTO infoTopicRegisterDTO, UriComponentsBuilder ucBuilder) {


        topicManagerService.statusInfo(
                infoTopicRegisterDTO.getTeacher().getId(),
                infoTopicRegisterDTO.getTopicProcessList().get(0).getInfoTopicRegister());
        for (TopicProcessDTO topicProcessDTO : infoTopicRegisterDTO.getTopicProcessList()) {
            topicManagerService.createTopicProcess(topicProcessDTO);
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}

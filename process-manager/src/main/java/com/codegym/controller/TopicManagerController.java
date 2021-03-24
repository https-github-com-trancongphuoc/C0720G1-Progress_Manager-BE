package com.codegym.controller;

import com.codegym.entity.InfoTopicRegister;
import com.codegym.entity.Student;
import com.codegym.entity.Topic;
import com.codegym.service.TopicManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public/topic-manager")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TopicManagerController {

    @Autowired
    TopicManagerService topicManagerService;

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
}

package com.codegym.controller;

import com.codegym.dto.CommentDTO;
import com.codegym.entity.Comment;
import com.codegym.entity.InfoTopicRegister;
import com.codegym.service.CommentService;
import com.codegym.service.InfoTopicRegisterService;
import com.codegym.service.TopicProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}

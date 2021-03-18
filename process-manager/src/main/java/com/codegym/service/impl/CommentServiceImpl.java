package com.codegym.service.impl;

import com.codegym.entity.Comment;
import com.codegym.repository.CommentRepository;
import com.codegym.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Override
    public Page<Comment> getListAppreciate(Integer id, Pageable pageable) {
        return commentRepository.findAllByTopicProcess_InfoTopicRegister_IdAndReplyCommentIsNull(id, pageable);
    }

    @Override
    public List<Comment> getListRepComment(Integer id) {
        return commentRepository.findAllByReplyComment_Id(id);
    }
}

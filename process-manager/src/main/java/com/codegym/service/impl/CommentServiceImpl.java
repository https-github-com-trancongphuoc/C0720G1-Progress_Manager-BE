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
        return commentRepository.findAllByTopicProcess_InfoTopicRegister_IdAndReplyCommentIsNullAndStatusTrueAndDeleteFlagFalseOrderByTimeCommentDesc(id, pageable);
    }

    @Override
    public List<Comment> getListRepComment(Integer id) {
        return commentRepository.findAllByReplyComment_IdAndDeleteFlagFalseOrderByTimeCommentDesc(id);
    }

    @Override
    public Comment teacherAppreciate(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment editAppreciate(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void deleteAppreciate(Comment comment) {
        commentRepository.save(comment);
    }
}

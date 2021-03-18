package com.codegym.service.impl;

import com.codegym.dto.CommentPostDTO;
import com.codegym.entity.Comment;
import com.codegym.repository.CommentPostRepository;
import com.codegym.service.CommentPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
@Service
public class CommentPostServiceImpl implements CommentPostService {

    @Autowired
    CommentPostRepository commentPostRepository;

    @Override
    public Comment findByIdComment(Integer id) {
        return commentPostRepository.findByIdComment(id);
    }

    @Override
    public void createCommentPost(CommentPostDTO commentPostDTO) {
        commentPostRepository.createCommentPost(commentPostDTO.getContent(), LocalDateTime.now().toString(), commentPostDTO.getAccountId(), false, false, commentPostDTO.getTitle(), commentPostDTO.getTopicProcessId());
    }

    @Override
    public void createReplyComment(CommentPostDTO commentPostDTO) {
        commentPostRepository.createReplyComment(commentPostDTO.getContent(), LocalDateTime.now().toString(), commentPostDTO.getAccountId(), false, false, commentPostDTO.getReplyCommentId());
    }

    @Override
    public void updateComment(CommentPostDTO commentPostDTO) {
        commentPostRepository.updateComment(commentPostDTO.getContent(), commentPostDTO.getId(), commentPostDTO.getAccountId());
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
    public void deleteComment(CommentPostDTO commentPostDTO) {
        commentPostRepository.deleteComment(true, commentPostDTO.getId(), commentPostDTO.getAccountId());
    }

    @Override
    public void deleteComment(Integer id, Integer accountId) {
        commentPostRepository.deleteComment(true, id, accountId);
    }

    @Override
    public void deleteComment(Integer id) {
        commentPostRepository.deleteComment(true, id);
    }


}

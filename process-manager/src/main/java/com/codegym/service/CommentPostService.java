package com.codegym.service;

import com.codegym.dto.CommentPostDTO;
import com.codegym.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentPostService {

    Comment findByIdComment(Integer id);

    void createCommentPost (CommentPostDTO commentPostDTO);

    void createReplyComment(CommentPostDTO commentPostDTO);

    void updateComment(CommentPostDTO commentPostDTO);

    Page<Comment> findAllByComment(Integer id, Pageable pageable);

    Page<Comment> findAllByCommentReply(Integer id, Pageable pageable);

    void deleteComment(CommentPostDTO commentPostDTO);
    void deleteComment(Integer id, Integer accountId);
    void deleteComment(Integer id);
}

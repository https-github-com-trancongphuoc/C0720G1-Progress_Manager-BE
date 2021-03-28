package com.codegym.service;

import com.codegym.dto.CommentPostDTO;
import com.codegym.dto.NotificationDTO;
import com.codegym.entity.Comment;
import com.codegym.entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public interface CommentPostService {

    Comment findByIdComment(Integer id);

    void createCommentPost (CommentPostDTO commentPostDTO);

    void updateCommentPost(CommentPostDTO commentPostDTO);

    void createReplyComment(CommentPostDTO commentPostDTO);

    void updateComment(CommentPostDTO commentPostDTO);

    Page<Comment> findAllByComment(Integer id, Pageable pageable);

    Page<Comment> findAllByCommentReply(Integer id, Pageable pageable);

    Page<Report> findAllByReport(Integer id, Pageable pageable);

    void deleteComment(Integer id, Integer accountId);

    void deleteComment(Integer id);

    void questionStudent(CommentPostDTO commentPostDTO) throws MessagingException, UnsupportedEncodingException;

    void createNotification(NotificationDTO notificationDTO);
}

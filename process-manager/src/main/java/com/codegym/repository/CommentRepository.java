package com.codegym.repository;

import com.codegym.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    Page<Comment> findAllByTopicProcess_InfoTopicRegister_IdAndReplyCommentIsNullAndStatusTrueAndDeleteFlagFalseOrderByTimeCommentDesc(Integer id, Pageable pageable);

    List<Comment> findAllByReplyComment_IdAndDeleteFlagFalseOrderByTimeCommentDesc(Integer replyComment_id);
}

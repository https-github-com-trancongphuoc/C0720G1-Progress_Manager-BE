package com.codegym.service;

import com.codegym.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {
    Page<Comment> getListAppreciate(Integer id, Pageable pageable);

    List<Comment> getListRepComment(Integer id);

    void teacherAppreciate(Comment comment);
}

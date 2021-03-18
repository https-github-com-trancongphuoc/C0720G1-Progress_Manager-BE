package com.codegym.dto;

import com.codegym.entity.Comment;

import java.util.List;

public class CommentDTO extends Comment {

    private List<Comment> replyCommentList;

    public CommentDTO() {
    }

    public CommentDTO(Comment comment) {
        super(comment.getId(), comment.getTimeComment(), comment.getTitle(), comment.getContent(), comment.getStatus(), comment.getTopicProcess(), comment.getAccount(), comment.getReplyComment());
    }

    public List<Comment> getReplyCommentList() {
        return replyCommentList;
    }

    public void setReplyCommentList(List<Comment> replyCommentList) {
        this.replyCommentList = replyCommentList;
    }
}

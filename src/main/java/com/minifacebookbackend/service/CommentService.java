package com.minifacebookbackend.service;

import com.minifacebookbackend.domain.model.Comment;

public interface CommentService {
    Comment saveComment(Comment comment);
    Comment updateComment(String commentId, Comment comment);
    void deleteComment(String commentId);
}

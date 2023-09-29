package com.minifacebookbackend.service;

import com.minifacebookbackend.domain.command.CommentCommand;
import com.minifacebookbackend.domain.model.Comment;

import java.util.List;

public interface CommentService {
    Comment saveComment(CommentCommand comment);
    Comment updateComment(CommentCommand commentCommand);
    void deleteComment(String commentId);
    void deleteComments(List<Comment> comments);

}

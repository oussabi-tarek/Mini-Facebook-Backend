package com.minifacebookbackend.service.impl;

import com.minifacebookbackend.domain.model.Comment;
import com.minifacebookbackend.repository.CommentRepository;
import com.minifacebookbackend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Override
    public Comment saveComment(Comment comment) {
        if(comment == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "comment to save is null");
        }
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(String commentId, Comment comment) {
        Comment commentToUpdate = commentRepository.findById(commentId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "comment id is not valid"));
        commentToUpdate.setComment(comment.getComment());
        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(String commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "comment id is not valid"));
        commentRepository.delete(comment);
    }
}

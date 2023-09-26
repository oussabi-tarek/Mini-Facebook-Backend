package com.minifacebookbackend.repository;

import com.minifacebookbackend.domain.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {
}

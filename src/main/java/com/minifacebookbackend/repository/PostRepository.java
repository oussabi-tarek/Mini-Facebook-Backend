package com.minifacebookbackend.repository;

import com.minifacebookbackend.domain.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String> {
}

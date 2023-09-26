package com.minifacebookbackend.repository;

import com.minifacebookbackend.domain.model.Like;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LikeRepository extends MongoRepository<Like, String> {
}

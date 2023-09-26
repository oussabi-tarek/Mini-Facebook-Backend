package com.minifacebookbackend.repository;

import com.minifacebookbackend.domain.model.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TagRepository extends MongoRepository<Tag, String> {
}

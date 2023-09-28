package com.minifacebookbackend.repository;

import com.minifacebookbackend.domain.model.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<Image, String> {
}
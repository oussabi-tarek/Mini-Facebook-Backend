package com.minifacebookbackend.service;

import com.minifacebookbackend.domain.model.Like;

public interface LikeService {
    Like saveLike(Like like);
    Like updateLike(String likeId, Like like);
    void deleteLike(String likeId);
}

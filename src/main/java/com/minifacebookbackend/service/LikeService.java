package com.minifacebookbackend.service;

import com.minifacebookbackend.domain.command.LikeCommand;
import com.minifacebookbackend.domain.model.Like;

import java.util.List;

public interface LikeService {
    Like saveLike(LikeCommand like);
    void deleteLike(String likeId);
    void deleteLikes(List<Like> likes);
}

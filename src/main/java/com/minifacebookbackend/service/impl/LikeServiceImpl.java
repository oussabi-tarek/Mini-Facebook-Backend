package com.minifacebookbackend.service.impl;

import com.minifacebookbackend.domain.model.Like;
import com.minifacebookbackend.repository.LikeRepository;
import com.minifacebookbackend.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeRepository likeRepository;
    @Override
    public Like saveLike(Like like) {
        if(like == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"like to save is null");
        }
        return likeRepository.save(like) ;
    }

    @Override
    public Like updateLike(String likeId, Like like) {
        Like likeToUpdate = likeRepository.findById(likeId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "like id is not valid"));
        likeToUpdate.setPost(like.getPost());
        return null;
    }

    @Override
    public void deleteLike(String likeId) {
        Like like = likeRepository.findById(likeId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "like id is not valid"));
        likeRepository.delete(like);
    }
}

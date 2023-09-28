package com.minifacebookbackend.service.impl;

import com.minifacebookbackend.domain.command.LikeCommand;
import com.minifacebookbackend.domain.model.Like;
import com.minifacebookbackend.repository.LikeRepository;
import com.minifacebookbackend.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    private LikeRepository likeRepository;
    @Override
    public Like saveLike(LikeCommand like) {
        if(like == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"like to save is null");
        }
        Like likeToSave = new Like();
        likeToSave.setUserId(like.getUserId());
        likeToSave.setPostId(like.getPostId());
        return likeRepository.save(likeToSave) ;
    }


    @Override
    public void deleteLike(String likeId) {
        Like like = likeRepository.findById(likeId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "like id is not valid"));
        likeRepository.delete(like);
    }
    @Override
    public void deleteLikes(List<Like> likes){
        likeRepository.deleteAll(likes);
    }


}

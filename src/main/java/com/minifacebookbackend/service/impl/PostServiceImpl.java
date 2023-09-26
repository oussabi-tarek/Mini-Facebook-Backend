package com.minifacebookbackend.service.impl;

import com.minifacebookbackend.domain.model.Post;
import com.minifacebookbackend.repository.PostRepository;
import com.minifacebookbackend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Override
    public Post savePost(Post post) {
        if(post == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "bad request");
        }
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(String postId, Post post) {
        Post postToUpdate = postRepository.findById(postId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "post id is not valid"));

        postToUpdate.setContent(post.getContent());
        postToUpdate.setUpdatedDate("");

        return postRepository.save(postToUpdate);
    }

    @Override
    public void deletePost(String postId) {
        Post postToDelete = postRepository.findById(postId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "post id is not valid"));

        postRepository.delete(postToDelete);

    }
}

package com.minifacebookbackend.service;

import com.minifacebookbackend.domain.model.Post;

public interface PostService {
    Post savePost(Post post);
    Post updatePost(String postId, Post post);
    void deletePost(String postId);
}

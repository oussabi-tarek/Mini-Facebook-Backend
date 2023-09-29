package com.minifacebookbackend.service;

import com.minifacebookbackend.domain.command.PostCommand;
import com.minifacebookbackend.domain.model.Post;
import com.minifacebookbackend.domain.representation.PostRepresentation;

import java.util.List;

public interface PostService {
    Post savePost(PostCommand postCommand);
    Post updatePost(PostCommand postCommand, String postId);
    void deletePost(String postId);
    PostRepresentation getPostById(String postId);
    List<PostRepresentation> getAll();
}

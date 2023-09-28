package com.minifacebookbackend.service.impl;

import com.minifacebookbackend.domain.command.PostCommand;
import com.minifacebookbackend.domain.model.Post;
import com.minifacebookbackend.domain.representation.PostRepresentation;
import com.minifacebookbackend.mapper.PostMapper;
import com.minifacebookbackend.repository.PostRepository;
import com.minifacebookbackend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final ImageServiceImpl imageService;
    private final TagServiceImpl tagService;
    private final CommentServiceImpl commentService;
    private final LikeServiceImpl likeService;

    @Override
    public PostRepresentation getPostById(String postId) {
        return postMapper.toPostRepresentation(postRepository.findById(postId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "post id is not valid")));
    }
    @Override
    public Post savePost(PostCommand postCommand) {
        if(postCommand == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "bad request");
        }
        Post post = new Post();
        post.setContent(postCommand.getContent());
        post.setCreatedDate(LocalDateTime.now().toString());
        post.setUserId(postCommand.getUserId());
        post.setImages(imageService.saveImages(postCommand.getImages()));
        post.setTags(tagService.saveTags(postCommand.getTags()));
        return postRepository.save(post);
    }


    @Override
    public Post updatePost(PostCommand postCommand, String postId) {
        Post postToUpdate = postRepository.findById(postId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "post id is not valid"));
        postToUpdate.setContent(postCommand.getContent());
        postToUpdate.setUpdatedDate(LocalDateTime.now().toString());
        imageService.updateImages(postCommand.getImages());
        tagService.updateTags(postCommand.getTags());
        return postRepository.save(postToUpdate);
    }

    @Override
    public void deletePost(String postId) {
        Post postToDelete = postRepository.findById(postId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "post id is not valid"));
        imageService.deleteImages(postToDelete.getImages());
        tagService.deleteTags(postToDelete.getTags());
        commentService.deleteComments(postToDelete.getComments());
        likeService.deleteLikes(postToDelete.getLikes());
        postRepository.delete(postToDelete);
    }
    @Override
    public List<PostRepresentation> getAll() {
        return postMapper.toPostRepresentationList(postRepository.findAll().stream().toList());
    }

}

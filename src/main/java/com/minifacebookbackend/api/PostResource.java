package com.minifacebookbackend.api;

import com.minifacebookbackend.api.common.ResourcePath;
import com.minifacebookbackend.domain.command.PostCommand;
import com.minifacebookbackend.domain.model.Post;
import com.minifacebookbackend.domain.representation.PostRepresentation;
import com.minifacebookbackend.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ResourcePath.POST)
@RequiredArgsConstructor
@Slf4j
public class PostResource {

    private final PostService postService;

    @GetMapping("/{postId}")
    public ResponseEntity<PostRepresentation> getPostDetails(@PathVariable String postId) {
        log.info("Recuperation des details du post: {}", postId);
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @GetMapping
    public ResponseEntity<List<PostRepresentation>> getAllPosts(){
        log.info("Recuperation de tous les posts");
        return ResponseEntity.ok(postService.getAll());
    }

    @PostMapping
    public ResponseEntity<Post> savePost(@RequestBody PostCommand postCommand) {
        log.info("ajouter un nouveau post : {}", postCommand);
        return ResponseEntity.ok(postService.savePost(postCommand));
    }
    @PutMapping("/{postId}")
    public ResponseEntity<Post> updatePost(@RequestBody PostCommand postCommand, @PathVariable String postId) {
        log.info("modifier le post : {} ", postCommand);
        return ResponseEntity.ok(postService.updatePost(postCommand, postId));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable String postId) {
        log.info("supprimer le post : {} ", postId);
        postService.deletePost(postId);
        return ResponseEntity.ok("Post deleted successfully");
    }



}

package com.minifacebookbackend.api;

import com.minifacebookbackend.api.common.ResourcePath;
import com.minifacebookbackend.domain.command.ImageCommand;
import com.minifacebookbackend.domain.command.PostCommand;
import com.minifacebookbackend.domain.criterias.PostCriteria;
import com.minifacebookbackend.domain.model.Post;
import com.minifacebookbackend.domain.representation.PostRepresentation;
import com.minifacebookbackend.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public ResponseEntity<List<PostRepresentation>> getAllPosts(PostCriteria postCriteria) {
        log.info("Recuperation de tous les posts {}", postCriteria);
        return ResponseEntity.ok(postService.getAll(postCriteria));
    }

    @PostMapping(consumes = {"multipart/form-data", "application/json"})
    public ResponseEntity<Post> savePost(@RequestPart("userId") String userId,
                                         @RequestPart("tags") String tags,
                                         @RequestPart("content") String content,
                                         @RequestPart("file") MultipartFile file) throws IOException {
        log.info("ajouter un nouveau post : {}",tags);
        return ResponseEntity.ok(postService.savePost(userId, content, file));
    }
    @PutMapping(value = "/{postId}", consumes = {"multipart/form-data", "application/json"})
    public ResponseEntity<PostRepresentation> updatePost(@RequestBody PostCommand postCommand, @PathVariable String postId) {
        log.info("modifier le post : {} ", postCommand);
        return ResponseEntity.ok(postService.updatePost(postCommand, postId));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable String postId) {
        log.info("supprimer le post : {} ", postId);
        postService.deletePost(postId);
        return ResponseEntity.ok("Post deleted successfully");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostRepresentation>> getPostsUsers(@PathVariable String userId) {
        log.info("Recuperation de tous les posts du userId {}");
        return ResponseEntity.ok(postService.findPostsByUserId(userId));
    }

}

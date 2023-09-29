package com.minifacebookbackend.api;

import com.minifacebookbackend.api.common.ResourcePath;
import com.minifacebookbackend.domain.command.LikeCommand;
import com.minifacebookbackend.domain.model.Like;
import com.minifacebookbackend.service.LikeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ResourcePath.LIKE)
@AllArgsConstructor
@Slf4j
public class LikeResource {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<Like> saveLike(LikeCommand likeCommand){
        log.info("create a new like {}", likeCommand);
        return ResponseEntity.ok(likeService.saveLike(likeCommand));
    }

    @DeleteMapping("/{likeId}")
    public ResponseEntity<String> deleteLike(@PathVariable String likeId){
        log.info("delete like with id {}", likeId);
        likeService.deleteLike(likeId);
        return ResponseEntity.ok("like deleted successfully");
    }


}

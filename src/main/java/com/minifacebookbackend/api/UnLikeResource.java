package com.minifacebookbackend.api;

import com.minifacebookbackend.api.common.ResourcePath;
import com.minifacebookbackend.domain.command.LikeCommand;
import com.minifacebookbackend.domain.command.UnLikeCommand;
import com.minifacebookbackend.domain.model.Like;
import com.minifacebookbackend.domain.model.UnLike;
import com.minifacebookbackend.service.LikeService;
import com.minifacebookbackend.service.UnlikeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ResourcePath.UNLIKE)
@AllArgsConstructor
@Slf4j
public class UnLikeResource {
    private final UnlikeService unlikeService;

    @PostMapping
    public ResponseEntity<UnLike> saveUnLike(@RequestBody UnLikeCommand unlikeCommand){
        log.info("unlike command : {}",unlikeCommand);
        return ResponseEntity.ok(unlikeService.saveUnLike(unlikeCommand));
    }

    @DeleteMapping("/{unlikeId}")
    public ResponseEntity<String> deleteUnLike(@PathVariable String unlikeId){
        log.info("unlike id : {}",unlikeId);
        unlikeService.deleteUnLike(unlikeId);
        return ResponseEntity.ok("Unlike deleted successfully");
    }
}

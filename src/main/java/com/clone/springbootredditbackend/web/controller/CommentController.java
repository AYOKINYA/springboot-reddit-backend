package com.clone.springbootredditbackend.web.controller;

import com.clone.springbootredditbackend.service.CommentService;
import com.clone.springbootredditbackend.web.dto.CommentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> createComment(@RequestBody CommentDto commentDto) {
        commentService.save(commentDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/by-post/{postId}")
    public ResponseEntity<List<CommentDto>> getAllCommentsForPost(@PathVariable Long postId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(commentService.getAllCommentsForPost(postId));

    }

    @GetMapping("/by-user/{userName}")
    public ResponseEntity<List<CommentDto>> getAllCommentsForUser(@PathVariable String userName) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(commentService.getAllCommentsForUser(userName));

    }
}

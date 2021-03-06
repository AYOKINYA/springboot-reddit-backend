package com.clone.springbootredditbackend.web.controller;

import com.clone.springbootredditbackend.service.SubredditService;
import com.clone.springbootredditbackend.web.dto.SubredditDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/subreddit")
@RequiredArgsConstructor
public class SubredditController {

    private final SubredditService subredditService;

    @GetMapping
    public ResponseEntity<List<SubredditDto>> getAllSubreddits() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subredditService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubredditDto> getSubreddit(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subredditService.getSubreddit(id));
    }

    @PostMapping
    public SubredditDto create(@RequestBody @Valid SubredditDto subredditDto) {
        return subredditService.save(subredditDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubredditDto> update(@PathVariable Long id, @RequestBody @Valid SubredditDto subredditDto) {
        subredditService.update(id, subredditDto);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(subredditService.getSubreddit(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        subredditService.delete(id);
    }
}

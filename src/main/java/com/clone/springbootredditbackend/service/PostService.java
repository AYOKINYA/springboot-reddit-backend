package com.clone.springbootredditbackend.service;

import com.clone.springbootredditbackend.Exception.PostNotFoundException;
import com.clone.springbootredditbackend.Exception.SubredditNotFoundException;
import com.clone.springbootredditbackend.domain.*;
import com.clone.springbootredditbackend.mapper.PostMapper;
import com.clone.springbootredditbackend.web.dto.PostRequest;
import com.clone.springbootredditbackend.web.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final SubredditRepository subredditRepository;
    private final PostMapper postMapper;
    private final AuthService authService;
    private final UserRepository userRepository;

    @Transactional
    public void save(PostRequest postRequest) {
        Subreddit subreddit =
                subredditRepository.findByName(postRequest.getSubredditName())
                        .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));

        postRepository.save(postMapper.map(postRequest, subreddit, authService.getCurrentUser()));
    }
    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }
    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(()->new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreddit(Long subredditId) {
        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(() -> new SubredditNotFoundException(
                        subredditId.toString()
                ));
        List<Post> posts = postRepository.findAllBySubreddit(subreddit);
        return posts.stream().map(postMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(toList());
    }

    @Transactional
    public void update(Long id, PostRequest postRequest) {
        Post post = postRepository.findById(id)
                .orElseThrow(()->new PostNotFoundException(id.toString()));

        Subreddit subreddit =
                subredditRepository.findByName(postRequest.getSubredditName())
                        .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));

        post.updatePost(postRequest.getSubredditName(), postRequest.getDescription(), postRequest.getUrl(), subreddit);
    }

    @Transactional
    public void delete(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(()->new PostNotFoundException(id.toString()));

        postRepository.delete(post);
    }
}

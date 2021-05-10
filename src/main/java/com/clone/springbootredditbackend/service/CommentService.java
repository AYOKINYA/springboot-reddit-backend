package com.clone.springbootredditbackend.service;

import com.clone.springbootredditbackend.Exception.PostNotFoundException;
import com.clone.springbootredditbackend.domain.*;
import com.clone.springbootredditbackend.mapper.CommentMapper;
import com.clone.springbootredditbackend.web.dto.CommentDto;
import com.clone.springbootredditbackend.web.dto.PostRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private static final String POST_URL = "";

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentMapper commentMapper;
    private final AuthService authService;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;
    private final UserRepository userRepository;

    public void save(CommentDto commentDto) {
        Post post = postRepository.findById(commentDto.getPostId()).
                orElseThrow(() -> new PostNotFoundException(
                        commentDto.getPostId().toString()
                ));
        Comment comment = commentMapper.map(commentDto, post, authService.getCurrentUser());
        commentRepository.save(comment);

        String message = mailContentBuilder.build(authService.getCurrentUser().getUsername() +
                " postsed a comment on your post." + POST_URL);
        sendCommentNotification(message, post.getUser());
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on your post", user.getEmail(), message));
    }

    public List<CommentDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(
                        postId.toString()
        ));
        return commentRepository.findByPost(post)
                .stream().map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public List<CommentDto>  getAllCommentsForUser(String userName) {
        User user = userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException(userName));
        return commentRepository.findAllByUser(user)
                .stream().map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public void update(Long id, CommentDto commentDto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(id.toString()));

        comment.updateComment(commentDto.getText());
    }
}

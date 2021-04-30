package com.clone.springbootredditbackend.service;

import com.clone.springbootredditbackend.Exception.PostNotFoundException;
import com.clone.springbootredditbackend.Exception.SpringRedditException;
import com.clone.springbootredditbackend.domain.Post;
import com.clone.springbootredditbackend.domain.PostRepository;
import com.clone.springbootredditbackend.domain.Vote;
import com.clone.springbootredditbackend.domain.VoteRepository;
import com.clone.springbootredditbackend.web.dto.VoteDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.clone.springbootredditbackend.domain.VoteType.UPVOTE;

@Service
@RequiredArgsConstructor
@Slf4j
public class VoteService {

    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final AuthService authService;

    @Transactional
    public void vote(VoteDto voteDto) {
        Post post = postRepository.findById(voteDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException("Post Not Found with ID - " + voteDto.getPostId()));

        Optional<Vote> voteByPostAndUser =
                voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
        if (voteByPostAndUser.isPresent() &&
        voteByPostAndUser.get().getVoteType().equals(voteDto.getVoteType())) {
            throw new SpringRedditException("You have already " + voteDto.getVoteType() +
                    "'d for this post");
        }

        if (UPVOTE.equals(voteDto.getVoteType())) {
            System.out.println("voteCount : " + post.getVoteCount());
            post.updateVoteCount(post.getVoteCount() + 1);
        } else {
            System.out.println("voteCount : " + post.getVoteCount());
            post.updateVoteCount(post.getVoteCount() - 1);
        }

        voteRepository.save(mapToVote(voteDto, post));
    }

    private Vote mapToVote(VoteDto voteDto, Post post) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }
}

package com.clone.springbootredditbackend.service;

import com.clone.springbootredditbackend.Exception.SpringRedditException;
import com.clone.springbootredditbackend.domain.Subreddit;
import com.clone.springbootredditbackend.domain.SubredditRepository;
import com.clone.springbootredditbackend.mapper.SubredditMapper;
import com.clone.springbootredditbackend.web.dto.SubredditDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubredditService {

    private final SubredditRepository subredditRepository;
    private final AuthService authService;
    private final SubredditMapper subredditMapper;

    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(subredditMapper::mapSubredditToDto)
                .collect(toList());

    }
    @Transactional(readOnly = true)
    public SubredditDto getSubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id).orElseThrow(
                () -> new SpringRedditException("Subreddit not found with id - " + id)
        );
        return subredditMapper.mapSubredditToDto(subreddit);
    }
    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit subreddit = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
        subredditDto.setId(subreddit.getId());
        return subredditDto;
    }

    @Transactional
    public void update(Long id, SubredditDto subredditDto) {
        Subreddit subreddit = subredditRepository.findById(id).orElseThrow(
                () -> new SpringRedditException("Subreddit not found with id - " + id)
        );
        subreddit.updateSubreddit(subredditDto.getName(), subredditDto.getDescription());
    }

    @Transactional
    public void delete(Long id) {
        Subreddit subreddit = subredditRepository.findById(id).orElseThrow(
                () -> new SpringRedditException("Subreddit not found with id - " + id)
        );
        subredditRepository.delete(subreddit);
    }

//   private SubredditDto mapToDto(Subreddit subreddit) {
//        return SubredditDto.builder()
//                .name(subreddit.getName())
//                .id(subreddit.getId())
//                .numberOfPosts(subreddit.getPosts().size())
//                .build();
//    }
//
//    private Subreddit mapToSubreddit(SubredditDto subredditDto) {
//        return Subreddit.builder()
//                .name("/r/" + subredditDto.getName())
//                .description(subredditDto.getDescription())
//                .user(authService.getCurrentUser())
//                .createdDate(Instant.now())
//                .build();
//    }
}

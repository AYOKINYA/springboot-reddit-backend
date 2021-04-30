package com.clone.springbootredditbackend.mapper;

import com.clone.springbootredditbackend.domain.Post;
import com.clone.springbootredditbackend.domain.Subreddit;
import com.clone.springbootredditbackend.service.AuthService;
import com.clone.springbootredditbackend.web.dto.SubredditDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
// This annotation specifies that this interface is a Mapstruct Mapper and Spring should identify it as a component and should be able to inject it into other components like SubredditService.
public abstract class SubredditMapper {

    @Autowired
    AuthService authService;

    @Mapping(target = "numberOfPosts", expression = "java(subreddit.getPosts().size())")
    public abstract SubredditDto mapSubredditToDto(Subreddit subreddit);

    @InheritInverseConfiguration
    //reverse mappings from SubredditDto to Subreddit by annotating a method with InheritInverseConfiguration.
    @Mapping(target = "posts", ignore = true)
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "user", expression = "java(authService.getCurrentUser())")
    public abstract Subreddit mapDtoToSubreddit(SubredditDto subredditDto);
}

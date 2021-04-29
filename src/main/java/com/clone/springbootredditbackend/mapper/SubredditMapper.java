package com.clone.springbootredditbackend.mapper;

import com.clone.springbootredditbackend.domain.Post;
import com.clone.springbootredditbackend.domain.Subreddit;
import com.clone.springbootredditbackend.web.dto.SubredditDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
// This annotation specifies that this interface is a Mapstruct Mapper and Spring should identify it as a component and should be able to inject it into other components like SubredditService.
public interface SubredditMapper {

    @Mapping(target = "numberOfPosts",
    expression = "java(mapPosts(subreddit.getPosts()))") //we are mapping from List<Posts> to an Integer, this kind of mapping is not straight forward and we need to write our logic.
    SubredditDto mapSubredditToDto(Subreddit subreddit);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }


    @InheritInverseConfiguration
    //reverse mappings from SubredditDto to Subreddit by annotating a method with InheritInverseConfiguration.
    @Mapping(target = "posts", ignore = true)
    Subreddit mapDtoToSubreddit(SubredditDto subredditDto);
}

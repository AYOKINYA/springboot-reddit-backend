package com.clone.springbootredditbackend.mapper;

import com.clone.springbootredditbackend.domain.Comment;
import com.clone.springbootredditbackend.domain.Post;
import com.clone.springbootredditbackend.domain.User;
import com.clone.springbootredditbackend.web.dto.CommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "commentDto.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source = "post")
    Comment map(CommentDto commentDto, Post post, User user);

    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "userName", expression = "java(comment.getUser().getUsername())")
    CommentDto mapToDto(Comment comment);
}

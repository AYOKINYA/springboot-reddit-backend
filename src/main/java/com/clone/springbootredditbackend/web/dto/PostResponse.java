package com.clone.springbootredditbackend.web.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class PostResponse {
    private Long id;
    private String postName;
    private String url;
    private String description;
    private String userName;
    private String subredditName;
    private Integer voteCount;
    private Integer commentCount;
    private String duration;
}

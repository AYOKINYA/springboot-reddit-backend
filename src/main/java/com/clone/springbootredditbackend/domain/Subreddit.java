package com.clone.springbootredditbackend.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Subreddit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Community name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Post> posts;

    private Instant createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn 어노테이션을 생략하면 아래와 같은 전략에 따라 외래 키를 매핑합니다.
    //필드명 +  “_” + 참조하는 테이블의 기본 키(@Id) 컬럼명
    private User user;

    @Builder
    public Subreddit(String name,String description,
                     List<Post> posts, Instant createdDate, User user) {
        this.name = name;
        this.description = description;
        this.posts = posts;
        this.createdDate = createdDate;
        this.user = user;
    }

    public void updateSubreddit(String name, String description) {
        this.name = name;
        this.description = description;
    }
}

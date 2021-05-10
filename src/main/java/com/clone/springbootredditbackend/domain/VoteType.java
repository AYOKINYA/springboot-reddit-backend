package com.clone.springbootredditbackend.domain;

public enum VoteType {
    UPVOTE(1),
    ZERO(0),
    DOWNVOTE(-1),;

    VoteType(int direction) {

    }
}

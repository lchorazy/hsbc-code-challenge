package com.hsbc.lukasz.networkingapp.web.model;

import com.hsbc.lukasz.networkingapp.db.model.DbPost;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class Post {

    String username;

    String message;

    LocalDateTime created;

    public static Post fromDb(DbPost dbPost) {
        return new Post(dbPost.getUser().getUsername(), dbPost.getMessage(), dbPost.getCreated());
    }
}

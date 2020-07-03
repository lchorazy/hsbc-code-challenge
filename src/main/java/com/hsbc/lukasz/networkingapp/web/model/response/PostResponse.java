package com.hsbc.lukasz.networkingapp.web.model.response;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class PostResponse {

    String username;

    String message;

    LocalDateTime created;
}

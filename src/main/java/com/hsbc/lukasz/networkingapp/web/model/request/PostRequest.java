package com.hsbc.lukasz.networkingapp.web.model.request;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class PostRequest {

    private String username;

    @Size(max = 140, message = "Message size cannot be greater than 140")
    private String message;
}

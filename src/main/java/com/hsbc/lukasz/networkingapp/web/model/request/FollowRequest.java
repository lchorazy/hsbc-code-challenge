package com.hsbc.lukasz.networkingapp.web.model.request;

import lombok.Value;

@Value
public class FollowRequest {

    String username;

    String followUsername;

}

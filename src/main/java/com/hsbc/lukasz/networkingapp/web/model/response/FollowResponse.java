package com.hsbc.lukasz.networkingapp.web.model.response;

import lombok.Value;

@Value
public class FollowResponse {

    String username;

    String followedUsername;

}

package com.hsbc.lukasz.networkingapp.web.controller;

import com.hsbc.lukasz.networkingapp.db.model.DbFollow;
import com.hsbc.lukasz.networkingapp.db.model.DbPost;
import com.hsbc.lukasz.networkingapp.db.model.DbUser;
import com.hsbc.lukasz.networkingapp.db.repository.FollowRepository;
import com.hsbc.lukasz.networkingapp.db.repository.PostRepository;
import com.hsbc.lukasz.networkingapp.db.repository.UserRepository;
import com.hsbc.lukasz.networkingapp.web.model.Post;
import com.hsbc.lukasz.networkingapp.web.model.request.FollowRequest;
import com.hsbc.lukasz.networkingapp.web.model.request.PostRequest;
import com.hsbc.lukasz.networkingapp.web.model.response.FollowResponse;
import com.hsbc.lukasz.networkingapp.web.model.response.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class NetworkingAppController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FollowRepository followRepository;

    @PostMapping("/post")
    public PostResponse post(@RequestBody PostRequest postRequest) {
        DbUser user = userRepository.getUser(postRequest.getUsername());
        DbPost post = postRepository.save(new DbPost(user, postRequest.getMessage()));
        return new PostResponse(post.getUser().getUsername(), post.getMessage(), post.getCreated());
    }

    @GetMapping("/wall")
    public List<Post> wall(@RequestParam String username) {
        DbUser user = userRepository.getUser(username);
        List<DbPost> posts = postRepository.findAllByUser(user);
        return posts.stream()
                .map(Post::fromDb)
                .sorted(Comparator.comparing(Post::getCreated).reversed())
                .collect(Collectors.toList());
    }

    @PostMapping("/follow")
    public FollowResponse follow(@RequestBody FollowRequest followRequest) {
        DbUser user = userRepository
                .getUser(followRequest.getUsername());
        DbUser followedUser = userRepository
                .getUser(followRequest.getFollowUsername());
        DbFollow followed = followRepository.save(new DbFollow(user, followedUser));
        return new FollowResponse(followed.getUser().getUsername(), followed.getFollowedUser().getUsername());
    }

    @GetMapping("/timeline")
    public List<Post> timeline(@RequestParam String username) {
        DbUser user = userRepository.getUser(username);
        List<DbFollow> followedDbUsers = followRepository.findAll(Example.of(new DbFollow(user, null)));
        List<DbPost> posts = postRepository.findAllByUserIn(
                followedDbUsers.stream()
                        .map(DbFollow::getFollowedUser)
                        .collect(Collectors.toList()));
        return posts.stream()
                .map(Post::fromDb)
                .sorted(Comparator.comparing(Post::getCreated).reversed())
                .collect(Collectors.toList());
    }
}

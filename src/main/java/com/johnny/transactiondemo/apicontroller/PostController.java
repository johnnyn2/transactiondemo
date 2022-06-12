package com.johnny.transactiondemo.apicontroller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.johnny.transactiondemo.model.Post;
import com.johnny.transactiondemo.model.request.AddPostRequest;
import com.johnny.transactiondemo.model.request.EditPostRequest;
import com.johnny.transactiondemo.model.response.PostResponse;
import com.johnny.transactiondemo.response.ApiResponse;
import com.johnny.transactiondemo.service.PostService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/post")
@Slf4j
public class PostController {
    @Autowired
    private PostService postService;
    
    @GetMapping("/list")
    public ResponseEntity<ApiResponse> getPosts(@RequestParam(required = true) int page) {
        try {
            List<Post> posts = postService.findAll();
            List<PostResponse> postList = posts
                .stream()
                .map(p -> new PostResponse(p))
                .collect(Collectors.toList());
            ApiResponse res = ApiResponse.builder()
                .message("success")
                .data(postList)
                .build();
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            String errMsg = e.getMessage();
            ApiResponse res = ApiResponse.builder()
                .message("fail")
                .data(errMsg)
                .build();
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addPost(@Valid @RequestBody(required = true) AddPostRequest post) {
        try {
            Post newPost = postService.addPost(post);
            PostResponse addPostResponse = new PostResponse(newPost);
            ApiResponse res = ApiResponse.builder()
                .message("success")
                .data(addPostResponse)
                .build();
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            String errMsg = e.getMessage();
            log.error(e.getMessage());
            ApiResponse res = ApiResponse.builder()
                .message("fail")
                .data(errMsg)
                .build();
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<ApiResponse> updatePost(@Valid @RequestBody(required = true) EditPostRequest post) {
        try {
            Post updatedPost = postService.updatePost(post);
            PostResponse updatedPostResponse = new PostResponse(updatedPost);
            ApiResponse res = ApiResponse.builder()
                .message("success")
                .data(updatedPostResponse)
                .build();
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            String errMsg = e.getMessage();
            log.error(e.getMessage());
            ApiResponse res = ApiResponse.builder()
                .message("fail")
                .data(errMsg)
                .build();
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/test")
    public String test() {
        return "ok";
    }
}

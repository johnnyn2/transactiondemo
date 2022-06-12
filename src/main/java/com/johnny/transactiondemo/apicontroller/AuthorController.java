package com.johnny.transactiondemo.apicontroller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.johnny.transactiondemo.model.Author;
import com.johnny.transactiondemo.model.request.AddAuthorRequest;
import com.johnny.transactiondemo.model.response.AuthorAndPostResponse;
import com.johnny.transactiondemo.model.response.AuthorResponse;
import com.johnny.transactiondemo.request.AuthorAndPost;
import com.johnny.transactiondemo.response.ApiResponse;
import com.johnny.transactiondemo.service.AuthorService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/author")
@Slf4j
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addAuthor(@Valid @RequestBody(required = true) AddAuthorRequest author) {
        try {
            Author newAuthor = authorService.addAuthor(author);
            AuthorResponse authorRes = new AuthorResponse(newAuthor);
            ApiResponse res = ApiResponse.builder()
                .message("success")
                .data(authorRes)
                .build();
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            String errMsg = e.getMessage();
            log.error(errMsg);
            ApiResponse res = ApiResponse.builder()
                .message("fail")
                .data(errMsg)
                .build();
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addAuthorAndPost")
    public ResponseEntity<ApiResponse> addAuthorAndPost(@Valid @RequestBody(required = true) AuthorAndPost authorAndPost) {
        try {
            AuthorAndPostResponse newAuthorAndPost = authorService.addAuthorAndPost(authorAndPost);
            ApiResponse res = ApiResponse.builder()
                .message("success")
                .data(newAuthorAndPost)
                .build();
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            String errMsg = e.getMessage();
            log.error(errMsg);
            ApiResponse res = ApiResponse.builder()
                .message("fail")
                .data(errMsg)
                .build();
            return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

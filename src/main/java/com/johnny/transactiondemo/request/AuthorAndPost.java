package com.johnny.transactiondemo.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.johnny.transactiondemo.model.Author;
import com.johnny.transactiondemo.model.Post;
import com.johnny.transactiondemo.model.request.AddAuthorRequest;
import com.johnny.transactiondemo.model.request.AddPostRequest;
import com.johnny.transactiondemo.model.request.AddPostWithoutAuthor;
import com.johnny.transactiondemo.util.Constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorAndPost {
    @NotNull(message = Constants.AUTHOR_REQUIRED)
    @Valid
    private AddAuthorRequest author;

    @NotNull(message = Constants.POST_REQUIRED)
    @Valid
    private AddPostWithoutAuthor post;
}

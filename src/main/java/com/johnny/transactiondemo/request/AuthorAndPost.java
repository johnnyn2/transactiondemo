package com.johnny.transactiondemo.request;

import javax.validation.Valid;

import com.johnny.transactiondemo.model.Author;
import com.johnny.transactiondemo.model.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorAndPost {
    @Valid
    private Author author;
    @Valid
    private Post post;
}

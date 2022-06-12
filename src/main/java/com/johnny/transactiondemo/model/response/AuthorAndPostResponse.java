package com.johnny.transactiondemo.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorAndPostResponse {
    private AuthorResponse author;
    private PostResponse post;
}

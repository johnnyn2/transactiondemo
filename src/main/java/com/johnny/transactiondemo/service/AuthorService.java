package com.johnny.transactiondemo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.johnny.transactiondemo.model.Author;
import com.johnny.transactiondemo.model.Post;
import com.johnny.transactiondemo.model.request.AddAuthorRequest;
import com.johnny.transactiondemo.model.response.AuthorAndPostResponse;
import com.johnny.transactiondemo.model.response.AuthorResponse;
import com.johnny.transactiondemo.model.response.PostResponse;
import com.johnny.transactiondemo.repository.AuthorRepository;
import com.johnny.transactiondemo.repository.PostRepository;
import com.johnny.transactiondemo.request.AuthorAndPost;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private PostRepository postRepository;

    @Transactional
    public Author addAuthor(AddAuthorRequest author) {
        Author newAuthor = Author.builder()
            .firstName(author.getFirstName())
            .lastName(author.getLastName())
            .gender(author.getGender())
            .build();
        return authorRepository.save(newAuthor);
    }

    @Transactional
    public AuthorAndPostResponse addAuthorAndPost(AuthorAndPost authorAndPost) {
        Author author = authorAndPost.getAuthor().createAuthor();
        Author newAuthor = authorRepository.save(author);
        // var x = 1/0;
        // an unchecked exception to test transaction rollback.
        // test result: we should not use try catch block in @transactional method.
        // using try catch block in @transactional method would block spring boot from rollback
        // We should use try catch in controller level 
        Post post = authorAndPost.getPost().createPostWithoutAuthor();
        post.setAuthor(newAuthor);
        Post newPost = postRepository.save(post);
        return new AuthorAndPostResponse(
            new AuthorResponse(newAuthor),
            new PostResponse(newPost));
    }
}

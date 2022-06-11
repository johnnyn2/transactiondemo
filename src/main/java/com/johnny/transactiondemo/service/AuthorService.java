package com.johnny.transactiondemo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.johnny.transactiondemo.model.Author;
import com.johnny.transactiondemo.model.Post;
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
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Transactional
    public AuthorAndPost addAuthorAndPost(AuthorAndPost authorAndPost) {
        Author author = authorAndPost.getAuthor();
        Author newAuthor = authorRepository.save(author);
        // var x = 1/0;
        // an unchecked exception to test transaction rollback.
        // test result: we should not use try catch block in @transactional method.
        // using try catch block in @transactional method would block spring boot from rollback
        // We should use try catch in controller level 
        Post post = authorAndPost.getPost();
        post.setAuthor(newAuthor);
        Post newPost = postRepository.save(post);
        return new AuthorAndPost(newAuthor, newPost);
    }
}

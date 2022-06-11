package com.johnny.transactiondemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.johnny.transactiondemo.model.Author;
import com.johnny.transactiondemo.model.Post;
import com.johnny.transactiondemo.model.request.AddPostRequest;
import com.johnny.transactiondemo.model.request.EditPostRequest;
import com.johnny.transactiondemo.repository.AuthorRepository;
import com.johnny.transactiondemo.repository.PostRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PostService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private PostRepository postRepository;
    
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post addPost(AddPostRequest post) {
        Author author = authorRepository.findById(post.getAuthorId()).get();
        Post newPost = Post.builder()
            .title(post.getTitle())
            .content(post.getContent())
            .author(author)
            .build();
        return postRepository.save(newPost);
    }

    @Transactional
    public Post updatePost(EditPostRequest post) {
        Optional<Post> postOptional = postRepository.findById(post.getId());
        Author author = authorRepository.findById(post.getAuthorId()).get();
        Post target = postOptional.get();
        target.setTitle(post.getTitle());
        target.setContent(post.getContent());
        target.setAuthor(author);
        return postRepository.save(target);
    }
}

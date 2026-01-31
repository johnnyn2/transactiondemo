package com.johnny.transactiondemo.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.johnny.transactiondemo.model.Author;
import com.johnny.transactiondemo.model.Post;
import com.johnny.transactiondemo.model.request.AddPostRequest;
import com.johnny.transactiondemo.model.request.EditPostRequest;
import com.johnny.transactiondemo.repository.AuthorRepository;
import com.johnny.transactiondemo.repository.PostRepository;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    private Author author;
    private Post post;

    @BeforeEach
    void setUp() {
        author = Author.builder()
            .id(1L)
            .firstName("John")
            .lastName("Doe")
            .build();

        post = Post.builder()
            .id(1L)
            .title("Sample Post")
            .content("This is a sample post.")
            .author(author)
            .build();
    }

    @DisplayName("Find All Posts - Success")
    @Test
    void testFindAll() {
        when(postRepository.findAll()).thenReturn(List.of(post));

        List<Post> posts = postService.findAll();

        assertNotNull(posts);
        assertEquals(1, posts.size());
        assertEquals("Sample Post", posts.get(0).getTitle());
    }

    @DisplayName("Add Post - Success")
    @Test
    void testAddPost() {
        AddPostRequest request = new AddPostRequest();
        request.setTitle("New Post");
        request.setContent("This is a new post.");
        request.setAuthorId(1L);

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(postRepository.save(any(Post.class))).thenReturn(post);

        Post savedPost = postService.addPost(request);

        assertNotNull(savedPost);
        assertEquals("Sample Post", savedPost.getTitle());
    }

    @DisplayName("Update Post - Success")
    @Test
    void testUpdatePost() {
        EditPostRequest request = new EditPostRequest();
        request.setId(1L);
        request.setTitle("Updated Post");
        request.setContent("This is an updated post.");
        request.setAuthorId(1L);

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(postRepository.save(any(Post.class))).thenReturn(post);

        Post updatedPost = postService.updatePost(request);

        assertNotNull(updatedPost);
        assertEquals("Updated Post", updatedPost.getTitle());
    }

    @DisplayName("testCreatePostWithoutAuthor - Success")
    @Test
    void testCreatePostWithoutAuthor() {
        AddPostRequest request = new AddPostRequest();
        request.setTitle("New Post");
        request.createPostWithoutAuthor();
        assertEquals("New Post", request.getTitle());
    }
}

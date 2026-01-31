package com.johnny.transactiondemo.apicontroller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.johnny.transactiondemo.model.Author;
import com.johnny.transactiondemo.model.Post;
import com.johnny.transactiondemo.model.request.AddPostRequest;
import com.johnny.transactiondemo.model.request.EditPostRequest;
import com.johnny.transactiondemo.service.PostService;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = PostController.class)
public class PostControllerTest {
    @MockBean
    private PostService postService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private List<Post> expectedPosts() {
        Author johnny = Author.builder()
            .id(1L)
            .firstName("Johnny")
            .lastName("Ng")
            .build();
        Author alice = Author.builder()
            .id(2L)
            .firstName("Alice")
            .lastName("Wong")
            .build();
        return List.of(
            Post.builder()
                .id(1L)
                .title("First Post")
                .content("This is the content of the first post.")
                .author(johnny)
                .build(),
            Post.builder()
                .id(2L)
                .title("Second Post")
                .content("This is the content of the second post.")
                .author(alice)
                .build()
        );
    }

    private AddPostRequest addPostRequest() {
        AddPostRequest req = new AddPostRequest();
        req.setTitle("New Post");
        req.setContent("This is a new post.");
        req.setAuthorId(1L);
        return req;
    }

    private Post expectedAddedPost() {
        Author johnny = Author.builder()
            .id(1L)
            .firstName("Johnny")
            .lastName("Ng")
            .build();
        return Post.builder()
            .id(3L)
            .title("New Post")
            .content("This is a new post.")
            .author(johnny)
            .build();
    }

    private EditPostRequest editPostRequest() {
        EditPostRequest req = new EditPostRequest();
        req.setId(1L);
        req.setTitle("Updated Post");
        req.setContent("This is an updated post.");
        req.setAuthorId(1L);
        return req;
    }

    private Post expectedEditedPost() {
        Author johnny = Author.builder()
            .id(1L)
            .firstName("Johnny")
            .lastName("Ng")
            .build();
        return Post.builder()
            .id(1L)
            .title("Updated Post")
            .content("This is an updated post.")
            .author(johnny)
            .build();
    }

    @DisplayName("/api/post/list 200 OK")
    @Test
    void whenValidGetPosts() throws Exception { 
        when(postService.findAll()).thenReturn(expectedPosts());
        mockMvc.perform(get("/api/post/list?page={page}", "0")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @DisplayName("/api/post/list 500 Internal Server Error")
    @Test
    void whenGetPostsThrowsException() throws Exception { 
        when(postService.findAll()).thenThrow(new RuntimeException("Database error"));
        mockMvc.perform(get("/api/post/list?page={page}", "0")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isInternalServerError());
    }

    @DisplayName("/api/post/add 200 OK")
    @Test
    void whenValidAddPost() throws Exception { 
        when(postService.addPost(addPostRequest())).thenReturn(expectedAddedPost());
        mockMvc.perform(post("/api/post/add")
            .content(objectMapper.writeValueAsString(addPostRequest()))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @DisplayName("/api/post/add 500 Internal Server Error")
    @Test
    void whenAddPostThrowsException() throws Exception { 
        when(postService.addPost(addPostRequest())).thenThrow(new RuntimeException("Database error"));
        mockMvc.perform(post("/api/post/add")
            .content(objectMapper.writeValueAsString(addPostRequest()))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isInternalServerError());
    }

    @DisplayName("/api/post/edit 200 OK")
    @Test
    void whenValidEditPost() throws Exception { 
        when(postService.updatePost(editPostRequest())).thenReturn(expectedEditedPost());
        mockMvc.perform(post("/api/post/edit")
            .content(objectMapper.writeValueAsString(editPostRequest()))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @DisplayName("/api/post/edit 500 Internal Server Error")
    @Test
    void whenEditPostThrowsException() throws Exception { 
        when(postService.updatePost(editPostRequest())).thenThrow(new RuntimeException("Database error"));
        mockMvc.perform(post("/api/post/edit")
            .content(objectMapper.writeValueAsString(editPostRequest()))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isInternalServerError());
    }

    @DisplayName("/api/post/test 200")
    @Test
    void testEndpoint() throws Exception { 
        mockMvc.perform(get("/api/post/test")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
}

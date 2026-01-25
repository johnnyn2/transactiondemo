package com.johnny.transactiondemo.apicontroller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.johnny.transactiondemo.enumerations.Gender;
import com.johnny.transactiondemo.model.Author;
import com.johnny.transactiondemo.model.Post;
import com.johnny.transactiondemo.model.request.AddAuthorRequest;
import com.johnny.transactiondemo.model.request.AddPostRequest;
import com.johnny.transactiondemo.model.request.AddPostWithoutAuthor;
import com.johnny.transactiondemo.model.response.AuthorAndPostResponse;
import com.johnny.transactiondemo.model.response.AuthorResponse;
import com.johnny.transactiondemo.model.response.PostResponse;
import com.johnny.transactiondemo.request.AuthorAndPost;
import com.johnny.transactiondemo.response.ApiResponse;
import com.johnny.transactiondemo.service.AuthorService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = AuthorController.class)
public class AuthorControllerTest {
    @MockBean
    private AuthorService authorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    private AddAuthorRequest addAuthorRequest() {
        AddAuthorRequest req = new AddAuthorRequest();
        req.setFirstName("Johnny");
        req.setLastName("Ng");
        req.setGender("M");
        return req;
    }

    private Author expectedAuthor() {
        return Author.builder()
            .id(1L)
            .firstName("Johnny")
            .lastName("Ng")
            .gender("M")
            .build();
    }

    private Post expectedPost() {
        return Post.builder()
            .id(1L)
            .title("First Post")
            .content("This is my first post.")
            .author(expectedAuthor())
            .build();
    }

    private AddPostWithoutAuthor addPostWithoutAuthorRequest() {
        AddPostWithoutAuthor req = new AddPostWithoutAuthor();
        req.setTitle("First Post");
        req.setContent("This is my first post.");
        return req;
    }

    private AuthorAndPostResponse expectedAuthorAndPostResponse() {
        // Implement if needed
        return new AuthorAndPostResponse(
            new AuthorResponse(expectedAuthor()),
            new PostResponse(expectedPost()));
    }

    private AuthorAndPost authorAndPostRequest() {
        AuthorAndPost req = new AuthorAndPost();
        req.setAuthor(addAuthorRequest());
        req.setPost(addPostWithoutAuthorRequest());
        return req;
    }

    @DisplayName("/api/author/add 200 OK")
    @Test
    void whenValidAuthor() throws Exception{
        // Mock the service layer as the controller invoke authorService.addAuthor() method
        when(authorService.addAuthor(addAuthorRequest())).thenReturn(expectedAuthor());
        mockMvc.perform(post("/api/author/add")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(addAuthorRequest())))
            .andExpect(status().isOk());
    }

    @DisplayName("/api/author/add 500 Error")
    @Test
    void whenInvalidAuthor() throws Exception{
        mockMvc.perform(post("/api/author/add")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(addAuthorRequest())))
            .andExpect(status().isInternalServerError());
    }

    @DisplayName("/addAuthorAndPost 200 OK")
    @Test
    void whenAddAuthorAndPost() throws Exception{
        // Mock the service layer as the controller invoke authorService.addAuthorAndPost() method
        when(authorService.addAuthorAndPost(authorAndPostRequest())).thenReturn(expectedAuthorAndPostResponse());

        mockMvc.perform(post("/api/author/addAuthorAndPost")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(authorAndPostRequest())))
            .andExpect(status().isOk());
    }

    @DisplayName("/addAuthorAndPost 500 Error")
    @Test
    void whenInvalidAddAuthorAndPost() throws Exception{
        when(authorService.addAuthorAndPost(authorAndPostRequest())).thenThrow(new Exception());
        mockMvc.perform(post("/api/author/addAuthorAndPost")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(authorAndPostRequest())))
            .andExpect(status().isInternalServerError());
    }
}
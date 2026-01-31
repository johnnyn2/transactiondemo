package com.johnny.transactiondemo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.johnny.transactiondemo.model.Author;
import com.johnny.transactiondemo.model.request.AddAuthorRequest;
import com.johnny.transactiondemo.model.request.AddPostWithoutAuthor;
import com.johnny.transactiondemo.model.response.AuthorAndPostResponse;
import com.johnny.transactiondemo.repository.AuthorRepository;
import com.johnny.transactiondemo.repository.PostRepository;
import com.johnny.transactiondemo.request.AuthorAndPost;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {
    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private AuthorService authorService;

    private Author author;

    @BeforeEach
    void setUp() {
        author = Author.builder()
            .firstName("John")
            .lastName("Doe")
            .gender("Male")
            .build();
    }

    private AddAuthorRequest addAuthorRequest() {
        AddAuthorRequest addAuthorRequest = new AddAuthorRequest();
        addAuthorRequest.setFirstName("John");
        addAuthorRequest.setLastName("Doe");
        addAuthorRequest.setGender("Male");
        return addAuthorRequest;
    }

    private AuthorAndPost authorAndPost() {
        AddAuthorRequest addAuthorRequest = addAuthorRequest();
        addAuthorRequest.setFirstName("John");
        addAuthorRequest.setLastName("Doe");
        addAuthorRequest.setGender("Male");
        AddPostWithoutAuthor addPostWithoutAuthor = new AddPostWithoutAuthor();
        addPostWithoutAuthor.setTitle("Sample Post");
        addPostWithoutAuthor.setContent("This is a sample post content.");
        // Post part can be null for this test
        return new AuthorAndPost(addAuthorRequest, addPostWithoutAuthor);
    }

    private AuthorAndPost authorAndPostKitty() {
        AddAuthorRequest addAuthorRequest = addAuthorRequest();
        addAuthorRequest.setFirstName("Kitty");
        addAuthorRequest.setLastName("Doe");
        addAuthorRequest.setGender("Female");
        AddPostWithoutAuthor addPostWithoutAuthor = new AddPostWithoutAuthor();
        addPostWithoutAuthor.setTitle("Sample Post");
        addPostWithoutAuthor.setContent("This is a sample post content.");
        // Post part can be null for this test
        return new AuthorAndPost(addAuthorRequest, addPostWithoutAuthor);
    }

    @DisplayName("Add Author - Success")
    @Test
    void testAddAuthor() {
        given(authorRepository.save(any(Author.class)))
            .willAnswer(invocation -> invocation.getArgument(0));
        Author savedAuthor = authorService.addAuthor(addAuthorRequest());
        assert savedAuthor.getFirstName().equals("John");
    }

    @DisplayName("Add AUthor and Post - Success")
    @Test
    void testAddAuthorAndPost() throws Exception {
        given(authorRepository.save(any(Author.class)))
            .willAnswer(invocation -> invocation.getArgument(0));
        given(postRepository.save(any()))
            .willAnswer(invocation -> invocation.getArgument(0));
        AuthorAndPostResponse authorAndPostResponse = authorService.addAuthorAndPost(authorAndPost());
        assert authorAndPostResponse.getAuthor().getFirstName().equals("John");
    }

    @DisplayName("Add Author and Post - Runtime exception")
    @Test
    void testAddAuthorAndPost_ThrowsException() throws Exception {
        // Arrange
        AuthorAndPost authorAndPostKitty = authorAndPostKitty(); // Create input with "Kitty" as first name
        given(authorRepository.save(any(Author.class)))
            .willAnswer(invocation -> invocation.getArgument(0));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authorService.addAuthorAndPost(authorAndPostKitty);
        });

        // Verify
        assertEquals(null, exception.getMessage()); // The exception has no message in the current implementation;

    }
}

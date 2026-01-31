package com.johnny.transactiondemo.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.johnny.transactiondemo.model.Author;

public class HelperTest {

    @DisplayName("Test getAuthorFullName")
    @Test
    void testGetAuthorFullName() {
        Helper helper = new Helper();
        assertNotNull(helper);
        // Arrange
        Author author = Author.builder()
            .firstName("John")
            .lastName("Doe")
            .build();

        // Act
        String fullName = Helper.getAuthorFullName(author);

        // Assert
        assertEquals("John Doe", fullName);
    }
}

package com.johnny.transactiondemo.model.response;

import java.sql.Timestamp;

import com.johnny.transactiondemo.model.Author;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponse {
    private String firstName;
    private String lastName;
    private String gender;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    public AuthorResponse(Author author) {
        this.firstName = author.getFirstName();
        this.lastName = author.getLastName();
        this.gender = author.getGender();
        this.createdAt = author.getCreateAt();
        this.updatedAt = author.getUpdatedAt();
    }
}

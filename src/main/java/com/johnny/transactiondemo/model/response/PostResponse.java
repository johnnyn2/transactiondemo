package com.johnny.transactiondemo.model.response;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostResponse {
    private String title;
    private String content;
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private String authorName;
}

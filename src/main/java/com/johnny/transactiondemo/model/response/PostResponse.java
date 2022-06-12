package com.johnny.transactiondemo.model.response;

import java.sql.Timestamp;

import com.johnny.transactiondemo.model.Post;
import com.johnny.transactiondemo.util.Helper;

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
    public PostResponse(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.createdAt = post.getCreateAt();
        this.updatedAt = post.getUpdatedAt();
        this.authorName = Helper.getAuthorFullName(post.getAuthor());
    }
}

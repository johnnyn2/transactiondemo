package com.johnny.transactiondemo.model.request;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.johnny.transactiondemo.model.Post;
import com.johnny.transactiondemo.sequence.FirstOrder;
import com.johnny.transactiondemo.sequence.SecondOrder;
import com.johnny.transactiondemo.util.Constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@GroupSequence({AddPostWithoutAuthor.class, FirstOrder.class, SecondOrder.class})
public class AddPostWithoutAuthor {
    @NotBlank(message = Constants.POST_TITLE_REQUIRED, groups = FirstOrder.class)
    private String title;
    
    @NotBlank(message = Constants.POST_CONTENT_REQUIRED, groups = FirstOrder.class)
    private String content;

    @JsonIgnore
    public Post createPostWithoutAuthor() {
        return Post.builder()
            .title(this.title)
            .content(this.content)
            .build();
    }
}

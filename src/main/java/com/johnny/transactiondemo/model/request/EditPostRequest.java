package com.johnny.transactiondemo.model.request;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.johnny.transactiondemo.sequence.FirstOrder;
import com.johnny.transactiondemo.sequence.SecondOrder;
import com.johnny.transactiondemo.util.Constants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@GroupSequence({EditPostRequest.class, FirstOrder.class, SecondOrder.class})
public class EditPostRequest {
    @NotNull(message = Constants.POST_ID_REQUIRED, groups = FirstOrder.class)
    private Long id;
    
    @NotBlank(message = Constants.POST_TITLE_REQUIRED, groups = FirstOrder.class)
    private String title;
    
    @NotBlank(message = Constants.POST_CONTENT_REQUIRED, groups = FirstOrder.class)
    private String content;
    
    @NotNull(message = Constants.POST_AUTHOR_ID_REQUIRED, groups = FirstOrder.class)
    private Long authorId;
}

package com.johnny.transactiondemo.model.request;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.johnny.transactiondemo.annotations.validations.GenderConstraint;
import com.johnny.transactiondemo.model.Author;
import com.johnny.transactiondemo.sequence.FirstOrder;
import com.johnny.transactiondemo.sequence.SecondOrder;
import com.johnny.transactiondemo.util.Constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@GroupSequence({AddAuthorRequest.class, FirstOrder.class, SecondOrder.class})
public class AddAuthorRequest {
    @NotBlank(message = Constants.AUTHOR_FIRST_NAME_REQUIRED, groups = FirstOrder.class)
    private String firstName;
    
    @NotBlank(message = Constants.AUTHOR_LAST_NAME_REQUIRED, groups = FirstOrder.class)
    private String lastName;
    
    @NotBlank(message = Constants.AUTHOR_GENDER_REQUIRED, groups = FirstOrder.class)
    @GenderConstraint(groups = SecondOrder.class)
    private String gender;

    @JsonIgnore
    public Author createAuthor() {
        return Author.builder()
            .firstName(this.firstName)
            .lastName(this.lastName)
            .gender(this.gender)
            .build();
    }
}

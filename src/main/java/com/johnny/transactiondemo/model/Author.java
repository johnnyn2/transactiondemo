package com.johnny.transactiondemo.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.johnny.transactiondemo.annotations.validations.GenderValidation;
import com.johnny.transactiondemo.enumerations.Gender;
import com.johnny.transactiondemo.sequence.FirstOrder;
import com.johnny.transactiondemo.sequence.SecondOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
@GroupSequence({Author.class, FirstOrder.class, SecondOrder.class})
@Entity
@Table(name = "Author")
public class Author extends TDModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Nullable
    private long id;

    @Column(name="first_name", nullable = false)
    @NotBlank(message = "Author first name is mandatory", groups = FirstOrder.class)
    private String firstName;

    @Column(name="last_name", nullable = false)
    @NotBlank(message = "Author last name is mandatory", groups = FirstOrder.class)
    private String lastName;

    @Column(name="gender", nullable = false)
    @NotBlank(message = "Author gender is mandatory", groups = FirstOrder.class)
    @GenderValidation(groups = SecondOrder.class)
    private String gender;

    @OneToMany(
        mappedBy = "author",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JsonIgnore
    private Set<Post> posts;

    @Version
    @Column(name = "version_id", nullable = false)
    @Nullable
    private int version;
}

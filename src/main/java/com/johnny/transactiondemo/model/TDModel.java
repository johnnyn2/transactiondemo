package com.johnny.transactiondemo.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.lang.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class TDModel {
    @Column(name="created_at", nullable = false)
    @CreationTimestamp
    @Nullable
    private Timestamp createAt;

    @Column(name="updated_at", nullable = false)
    @UpdateTimestamp
    @Nullable
    private Timestamp updatedAt;
}

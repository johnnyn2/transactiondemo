package com.johnny.transactiondemo.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.johnny.transactiondemo.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    public <S extends Post> S save(Post post);
    
    @Lock(LockModeType.OPTIMISTIC)
    public  Optional<Post> findById(Long id);
}

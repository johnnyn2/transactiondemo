package com.johnny.transactiondemo.repository;

import java.util.Optional;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import com.johnny.transactiondemo.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    public <S extends Author> S save(Author post);
    
    @Lock(LockModeType.OPTIMISTIC)
    public  Optional<Author> findById(Long id);
}

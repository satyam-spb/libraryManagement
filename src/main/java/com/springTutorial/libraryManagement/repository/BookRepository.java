package com.springTutorial.libraryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springTutorial.libraryManagement.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    
}

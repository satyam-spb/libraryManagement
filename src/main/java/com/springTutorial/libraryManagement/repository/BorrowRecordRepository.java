package com.springTutorial.libraryManagement.repository;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springTutorial.libraryManagement.entity.Book;
import com.springTutorial.libraryManagement.entity.BorrowRecord;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long>{

    @Query("SELECT b FROM BorrowRecord br JOIN br.book b " + "WHERE br.borrowDate >= :startDate AND br.borrowDate <= :endDate " + "GROUP BY b " + "ORDER BY COUNT(br) DESC")
    List<Book> findMostBorrowedBook(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
    
}

package com.springTutorial.libraryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springTutorial.libraryManagement.entity.BorrowRecord;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long>{
    
}

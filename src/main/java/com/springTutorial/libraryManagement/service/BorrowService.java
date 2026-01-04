package com.springTutorial.libraryManagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springTutorial.libraryManagement.entity.BorrowRecord;
import com.springTutorial.libraryManagement.exception.BorrowRecordNotFoundException;
import com.springTutorial.libraryManagement.repository.BorrowRecordRepository;
import com.springTutorial.libraryManagement.repository.BorrowRecordRepository;

@Service
public class BorrowService {
    private final BorrowRecordRepository borrowRecordRepository;
    public BorrowService(BorrowRecordRepository borrowRecordRepository){
        this.borrowRecordRepository = borrowRecordRepository;
    }

    public BorrowRecord createBorrowRecord(BorrowRecord borrowRecord){
        return borrowRecordRepository.save(borrowRecord);
    }

    public List<BorrowRecord> getAllBorrowRecords(){
        return borrowRecordRepository.findAll();
    }

    public BorrowRecord getBorrowRecordById(Long id){
        return borrowRecordRepository.findById(id)
        .orElseThrow(() -> new BorrowRecordNotFoundException("BorrowRecord with id %d not found".formatted(id)));
    }


    public void deleteBorrowRecord(Long id){
        borrowRecordRepository.deleteById(id);
    }
}

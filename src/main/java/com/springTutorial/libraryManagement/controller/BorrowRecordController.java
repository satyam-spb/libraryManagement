package com.springTutorial.libraryManagement.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springTutorial.libraryManagement.entity.Book;
import com.springTutorial.libraryManagement.entity.BorrowRecord;
import com.springTutorial.libraryManagement.service.BorrowRecordService;

@RestController
@RequestMapping("/api/borrow-records")
public class BorrowRecordController {
    public final BorrowRecordService borrowRecordService;

    public BorrowRecordController(BorrowRecordService borrowRecordService){
        this.borrowRecordService = borrowRecordService;
    }

    @PostMapping
    public BorrowRecord createBorrowRecord(@RequestBody BorrowRecord borrowRecord){
        return borrowRecordService.createBorrowRecord(borrowRecord);
    }

    @GetMapping
    public List<BorrowRecord> getAllBorrowRecords(){
        return borrowRecordService.getAllBorrowRecords();
    }

    @GetMapping("/{borrow_id}")
    public BorrowRecord getBorrowRecordById(@PathVariable Long borrow_id){
        return borrowRecordService.getBorrowRecordById(borrow_id);
    }

    @GetMapping("/most-borrowed/{year}/{month}")
    public Book getMostBorrowedBook(@PathVariable int year, @PathVariable int month){
        return borrowRecordService.getMostBorrowedBookByMonth(year,month);
    }

    @DeleteMapping("/{id}")
    public void deleteBorrowRecord(@PathVariable Long borrow_id){
        borrowRecordService.deleteBorrowRecord(borrow_id);
    }
}

package com.springTutorial.libraryManagement.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class BorrowRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long borrow_id;

    @Column
    private LocalDateTime borrowDate;

    @Column
    private LocalDateTime returnDate;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id", nullable = false)
    private User user;

    // JPA (Hibernate) requires a no-argument constructor (a constructor with no parameters) for every
    // entity. It uses this to create an empty object before filling it with data from the database.

    public BorrowRecord(){}

    //getters and setters
    public long getBorrowId(){
        return borrow_id;
    }

    public LocalDateTime getBorrowDate(){
        return borrowDate;
    }

    public LocalDateTime getReturnDate(){
        return returnDate;
    }

    public Book getBook(){
        return book;
    }

    public User getUser(){
        return user;
    }

    public void setBorrowDate(LocalDateTime borrowDate){
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(LocalDateTime returnDate){
        this.returnDate = returnDate;
    }

    public void setBook(Book book){
        this.book = book;
    }

    public void setUser(User user){
        this.user = user;
    }
}

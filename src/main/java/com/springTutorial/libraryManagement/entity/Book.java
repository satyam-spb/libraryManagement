package com.springTutorial.libraryManagement.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(unique = true, nullable = false)
    private String isbn;

    @Column()
    private int totalCopies;

    @Column()
    private int availableCopies;

    // JPA (Hibernate) requires a no-argument constructor (a constructor with no parameters) for every
    // entity. It uses this to create an empty object before filling it with data from the database.
    public Book(){}

    // Getters and setters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTotalCopies(int totalCopies){
        this.totalCopies = totalCopies;
    }

    public void setAvailableCopies(int availableCopies){
        this.availableCopies = availableCopies;
    }

    public int getAvailableCopies(){
        return availableCopies;
    }

    public int getTotalCopies(){
        return totalCopies;
    }

}

package com.springTutorial.libraryManagement.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springTutorial.libraryManagement.entity.Book;
import com.springTutorial.libraryManagement.exception.BookNotFoundException;
import com.springTutorial.libraryManagement.repository.BookRepository;


@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book){
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(Long id){
        return bookRepository.findById(id)
        .orElseThrow(() -> new BookNotFoundException("Book with id %d not found".formatted(id)));
    }

    public Book updateBook(Long id, Book updatedBook){
        Book existingBook = getBookById(id);
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setIsbn(updatedBook.getIsbn());

        return bookRepository.save(existingBook);
    }

    public void deleteBook(Long id){
        bookRepository.deleteById(id);
    }
}

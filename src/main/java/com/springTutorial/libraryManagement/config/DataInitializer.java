package com.springTutorial.libraryManagement.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springTutorial.libraryManagement.entity.Book;
import com.springTutorial.libraryManagement.service.BookService;

@Configuration
public class DataInitializer {
    
    @Bean
    CommandLineRunner initBooks(BookService bookService){
        return args -> {
            if (bookService.getAllBooks().isEmpty()) {
                for (int i = 1; i <= 10; i++) {
                    Book book = new Book();
                    book.setTitle("Book " + i);
                    book.setAuthor("Author " + i);
                    book.setIsbn("ISBN-000" + i);
                    book.setTotalCopies(5);
                    book.setAvailableCopies(5);

                    bookService.createBook(book);
                }
            }

        };
    }
}

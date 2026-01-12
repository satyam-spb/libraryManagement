package com.springTutorial.libraryManagement.config;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import com.springTutorial.libraryManagement.entity.Book;
import com.springTutorial.libraryManagement.entity.BorrowRecord;
import com.springTutorial.libraryManagement.entity.User;
import com.springTutorial.libraryManagement.repository.BorrowRecordRepository;
import com.springTutorial.libraryManagement.service.BookService;
import com.springTutorial.libraryManagement.service.BorrowRecordService;
import com.springTutorial.libraryManagement.service.UserService;

@Configuration
public class DataInitializer {
    
    @Bean
    CommandLineRunner initBooks(BookService bookService, UserService userService, BorrowRecordService borrowRecordService,BorrowRecordRepository borrowRecordRepository){
        return args -> {
            
            //initializing books
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

            //initializing users
            if(userService.getAllUsers().isEmpty()){
                for(int i = 1; i <= 6; i++){
                    User user = new User();
                    user.setUserName("User " + i);
                    userService.createUser(user);
                }
            }

            //initializing borrow records (using existing users and books)
            if(borrowRecordService.getAllBorrowRecords().isEmpty()){
                borrowRecordService.initializeHistory(userService, bookService);
            }

        };
    }
}

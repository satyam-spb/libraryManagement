package com.springTutorial.libraryManagement.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springTutorial.libraryManagement.entity.Book;
import com.springTutorial.libraryManagement.entity.BorrowRecord;
import com.springTutorial.libraryManagement.entity.User;
import com.springTutorial.libraryManagement.exception.BookAlreadyReturnedException;
import com.springTutorial.libraryManagement.exception.BookNotAvailableException;
import com.springTutorial.libraryManagement.exception.BookNotFoundException;
import com.springTutorial.libraryManagement.exception.BorrowRecordNotFoundException;
import com.springTutorial.libraryManagement.exception.UserNotFoundException;
import com.springTutorial.libraryManagement.repository.BookRepository;
import com.springTutorial.libraryManagement.repository.BorrowRecordRepository;
import com.springTutorial.libraryManagement.repository.UserRepository;


@Service
public class BorrowRecordService {
    private final BorrowRecordRepository borrowRecordRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BorrowRecordService(BorrowRecordRepository borrowRecordRepository, BookRepository bookRepository, UserRepository userRepository){
        this.borrowRecordRepository = borrowRecordRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public BorrowRecord createBorrowRecord(BorrowRecord borrowRecord){

        //check if user exists
        long user_id = borrowRecord.getUser().getUserId();
        User user = userRepository.findById(user_id).orElseThrow(() -> new UserNotFoundException("This user does not exist in the library database"));

        //check if book exists
        long bookId = borrowRecord.getBook().getId();
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("This book does not exist in our library"));

        //check if book is available
        if(book.getAvailableCopies() < 1){
            throw new BookNotAvailableException("The book %s is currently not available".formatted(book.getTitle()));
        }

        //creating a borrow record(inc saving user and book)
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);
        borrowRecord.setBook(book);
        borrowRecord.setUser(user);
        borrowRecord.setBorrowDate(LocalDateTime.now());
        return borrowRecordRepository.save(borrowRecord);
    }

    public List<BorrowRecord> getAllBorrowRecords(){
        return borrowRecordRepository.findAll();
    }

    public BorrowRecord getBorrowRecordById(Long id){
        return borrowRecordRepository.findById(id)
        .orElseThrow(() -> new BorrowRecordNotFoundException("BorrowRecord with id %d not found".formatted(id)));
    }

    @Transactional
    public String returnBookByBorrowRecordId(Long Id){

        BorrowRecord borrowRecord = getBorrowRecordById(Id);

        //check if book already returned
        if(borrowRecord.getReturnDate() != null){
            throw new BookAlreadyReturnedException("This book is already returned");
        }

        borrowRecord.setReturnDate(LocalDateTime.now());

        //update available copies of the returned book
        long bookId = borrowRecord.getBook().getId();
        Book book = borrowRecord.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        borrowRecordRepository.save(borrowRecord);
        return "Book returned successfully";
    }

    public Book getMostBorrowedBookByMonth(int year, int month) {
        
        // Start of the month at 00:00:00
        LocalDate startDay = LocalDate.of(year, month, 1);
        LocalDateTime startDateTime = startDay.atStartOfDay();
        
        // End of the month at 23:59:59
        LocalDate endDay = startDay.withDayOfMonth(startDay.lengthOfMonth());
        LocalDateTime endDateTime = endDay.atTime(LocalTime.MAX);

        // Create the pageable request for just the top 1
        Pageable topOne = PageRequest.of(0, 1);
        
        List<Book> result = borrowRecordRepository.findMostBorrowedBook(startDateTime, endDateTime, topOne);

        return result.isEmpty() ? null : result.get(0);
    }

    @Transactional
    public String initializeHistory(UserService userService, BookService bookService){
        List<User> allUsers = userService.getAllUsers();
        List<Book> allBooks = bookService.getAllBooks();

        for(int month = 1;  month <= 12; month++){
            int borrowThisMonth = (int)(Math.random() * 5) + 1;
            for(int numOfBooks = 1; numOfBooks <= borrowThisMonth; numOfBooks++){

                //get a user and a book (random)
                User randomUser = allUsers.get((int)(Math.random() * allUsers.size()));
                Book randomBook = allBooks.get((int)(Math.random() * allBooks.size()));

                //check available copies
                if(randomBook.getAvailableCopies() < 1)
                    continue;
                            

                //create a borrow record and save it to borrow record table(also ensure availableCopies are correctly updated)
                BorrowRecord randomBorrowRecord = new BorrowRecord();
                int randomBorrowDay = (int)(Math.random() * 14) + 1;
                randomBorrowRecord.setBorrowDate(LocalDateTime.of(2025, month, randomBorrowDay, 13,03));
                randomBorrowRecord.setBook(randomBook);
                randomBook.setAvailableCopies(randomBook.getAvailableCopies() - 1);
                randomBorrowRecord.setUser(randomUser);
                bookService.createBook(randomBook);

                //return the book
                LocalDate date = LocalDate.of(2025, month, 1);
                int randomReturnDay = date.lengthOfMonth() - (int)(Math.random() * 14);
                randomBorrowRecord.setReturnDate(LocalDateTime.of(2025,month, randomReturnDay, 19,02));
                randomBook.setAvailableCopies(randomBook.getAvailableCopies() + 1);
                bookService.createBook(randomBook);

                borrowRecordRepository.save(randomBorrowRecord);
                        
            }
        }
        return "Borrow Record Successfully Initialized";
    }


    public void deleteBorrowRecord(Long id){
        borrowRecordRepository.deleteById(id);
    }
}

package com.example.assessment.service;

import com.example.assessment.entity.Book;
import com.example.assessment.entity.Borrower;
import com.example.assessment.repository.BookRepository;
import com.example.assessment.repository.BorrowerRepository;
import com.example.assessment.web.request.AddBorrowerRequest;
import com.example.assessment.web.response.BorrowBookResponse;
import com.example.assessment.web.response.BorrowerResponse;
import com.example.assessment.web.response.ReturnBookResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class BorrowerService {

    private final BorrowerRepository borrowerRepository;
    private final BookRepository bookRepository;

    @Transactional
    public BorrowerResponse addBorrower(AddBorrowerRequest request) {
        Borrower savedBorrower = borrowerRepository
                .save(Borrower.builder()
                              .name(request.getName())
                              .email(request.getEmail())
                              .build());

        return BorrowerResponse.builder()
                               .id(savedBorrower.getId())
                               .name(savedBorrower.getName())
                               .email(savedBorrower.getEmail())
                               .build();
    }


    @Transactional
    public BorrowBookResponse borrowBook(Long borrowerId, Long bookId) {
        // Fetch book by ID
        Book book = bookRepository.findById(bookId)
                                  .orElseThrow(() -> new NoSuchElementException("Book not found with id: " + bookId));

        // Check if the book is already borrowed
        if (book.getBorrowedBy() != null) {
            throw new IllegalStateException("Book with id " + bookId + " is already borrowed");
        }

        // Fetch borrower by ID
        Borrower borrower = borrowerRepository.findById(borrowerId)
                                              .orElseThrow(() -> new NoSuchElementException("Borrower not found with " +
                                                      "id: " + borrowerId));

        // Assign borrower to the book
        book.setBorrowedBy(borrower);

        // Save and return response
        Book updated = bookRepository.save(book);

        return BorrowBookResponse.builder()
                                 .bookId(updated.getId())
                                 .isbn(updated.getIsbn())
                                 .title(updated.getTitle())
                                 .author(updated.getAuthor())
                                 .borrowedById(borrower.getId())
                                 .borrowedByName(borrower.getName())
                                 .build();
    }

    @Transactional
    public ReturnBookResponse returnBook(Long borrowerId, Long bookId) {
        // Fetch book by ID
        Book book = bookRepository.findById(bookId)
                                  .orElseThrow(() -> new NoSuchElementException("Book not found with id: " + bookId));

        // Check if the book is currently borrowed
        if (book.getBorrowedBy() == null) {
            throw new IllegalStateException("Book with id " + bookId + " is not currently borrowed");
        }

        // Get borrower info before clearing (for response)
        Borrower borrower = book.getBorrowedBy();

        // Clear borrower
        book.setBorrowedBy(null);

        // Save updated book
        Book updated = bookRepository.save(book);

        return ReturnBookResponse.builder()
                                 .bookId(updated.getId())
                                 .isbn(updated.getIsbn())
                                 .title(updated.getTitle())
                                 .author(updated.getAuthor())
                                 .returnedById(borrower.getId())
                                 .returnedByName(borrower.getName())
                                 .build();
    }

}

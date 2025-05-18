package com.example.assessment.service;

import com.example.assessment.entity.Book;
import com.example.assessment.repository.BookRepository;
import com.example.assessment.web.request.AddBookRequest;
import com.example.assessment.web.response.BookResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;


    @Transactional
    public void addBook(AddBookRequest request) {
        // Build Book entity from request
        Book book = Book.builder()
                        .isbn(request.getIsbn())
                        .title(request.getTitle())
                        .author(request.getAuthor())
                        .build();

        // Check ISBN conflict
        boolean isbnConflict = bookRepository.existsByIsbnAndTitleAndAuthorNot(
                book.getIsbn(), book.getTitle(), book.getAuthor()
        );

        if (isbnConflict) {
            throw new IllegalArgumentException(
                    "ISBN conflict: Book with this ISBN must have the same title and author"
            );
        }

        // Save and return the saved book entity
        bookRepository.save(book);
    }


    /**
     * Retrieves all books in the library.
     */
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll()
                             .stream()
                             .map(book ->
                                     BookResponse.builder()
                                                 .isbn(book.getIsbn())
                                                 .title(book.getTitle())
                                                 .author(book.getAuthor())
                                                 .build())
                             .collect(Collectors.toList());

    }
}

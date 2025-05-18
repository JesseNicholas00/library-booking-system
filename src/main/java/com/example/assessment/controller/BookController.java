package com.example.assessment.controller;

import com.example.assessment.entity.Book;
import com.example.assessment.service.BookService;
import com.example.assessment.web.request.AddBookRequest;
import com.example.assessment.web.response.BaseResponse;
import com.example.assessment.web.response.BookResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BaseResponse<Void>> addBook(@RequestBody @Valid AddBookRequest request) {
        bookService.addBook(request);
        return ResponseEntity.ok(
                BaseResponse.<Void>builder()
                            .message("Book added successfully")
                            .build());
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<BookResponse>>> getAllBooks() {
        List<BookResponse> bookResponses = bookService.getAllBooks();
        return ResponseEntity.ok(
                BaseResponse.<List<BookResponse>>builder()
                            .message(String.format("Total book quantity %d", bookResponses.size()))
                            .data(bookResponses)
                            .build());
    }
}

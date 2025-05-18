package com.example.assessment.controller;

import com.example.assessment.service.BorrowerService;
import com.example.assessment.web.request.AddBorrowerRequest;
import com.example.assessment.web.response.BaseResponse;
import com.example.assessment.web.response.BorrowBookResponse;
import com.example.assessment.web.response.BorrowerResponse;
import com.example.assessment.web.response.ReturnBookResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/borrowers")
@RequiredArgsConstructor
public class BorrowerController {
    private final BorrowerService borrowerService;

    @PostMapping
    public ResponseEntity<BaseResponse<BorrowerResponse>> registerBorrower(
            @RequestBody @Valid AddBorrowerRequest request) {
        BorrowerResponse borrowerResponse = borrowerService.addBorrower(request);
        return ResponseEntity.ok(
                BaseResponse.<BorrowerResponse>builder()
                            .message("Book added successfully")
                            .data(borrowerResponse)
                            .build());
    }

    @PostMapping("/{borrowerId}/borrow/{bookId}")
    public ResponseEntity<BaseResponse<BorrowBookResponse>> borrowBook(@PathVariable(value = "borrowerId") Long borrowerId,
                                                                       @PathVariable(value = "bookId") Long bookId) {
        BorrowBookResponse borrowBookResponse = borrowerService.borrowBook(borrowerId, bookId);
        return ResponseEntity.ok(
                BaseResponse.<BorrowBookResponse>builder()
                            .message("Book added successfully")
                            .data(borrowBookResponse)
                            .build());
    }

    @PostMapping("/{borrowerId}/return/{bookId}")
    public ResponseEntity<BaseResponse<ReturnBookResponse>> returnBook(@PathVariable(value = "borrowerId") Long borrowerId,
                                                                       @PathVariable(value = "bookId") Long bookId) {
        ReturnBookResponse returnBookResponse = borrowerService.returnBook(borrowerId, bookId);
        return ResponseEntity.ok(
                BaseResponse.<ReturnBookResponse>builder()
                            .message("Book added successfully")
                            .data(returnBookResponse)
                            .build());
    }
}

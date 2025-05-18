package com.example.assessment.web.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BorrowBookResponse {
    private Long bookId;
    private String isbn;
    private String title;
    private String author;
    private Long borrowedById;
    private String borrowedByName;
}
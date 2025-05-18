package com.example.assessment.repository;

import com.example.assessment.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    boolean existsByIsbnAndTitleAndAuthorNot(String isbn, String title, String author);
}
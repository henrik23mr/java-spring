package com.henrik23mr.crud_bookstore_demo1.repository;

import com.henrik23mr.crud_bookstore_demo1.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findBookById(Long id);
    Optional<List<String>> findBookByAuthor(String author);
    Optional<String> findBookByIsbn(String isbn);
}

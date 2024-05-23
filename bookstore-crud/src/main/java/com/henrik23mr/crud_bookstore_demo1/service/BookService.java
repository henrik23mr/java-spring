package com.henrik23mr.crud_bookstore_demo1.service;

import com.henrik23mr.crud_bookstore_demo1.entity.Book;
import com.henrik23mr.crud_bookstore_demo1.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public Optional<List<Book>> findAll(){
        return Optional.of(repository.findAll());
    }

    public Optional<Book> findbyId(Long id){
        return repository.findBookById(id);
    }

    public Book saveBook(Book book){
        return repository.save(book);
    }

    public void deleteBook(Long id){
        repository.deleteById(id);
    }

    public Book updateBook(Long id, Book updateBook){
        Book book = repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setTitle(updateBook.getTitle());
        book.setAuthor(updateBook.getAuthor());
        book.setIsbn(updateBook.getIsbn());

        return repository.save(book);

    }

}

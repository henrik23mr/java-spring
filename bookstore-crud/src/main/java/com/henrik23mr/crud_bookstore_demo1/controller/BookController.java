package com.henrik23mr.crud_bookstore_demo1.controller;

import com.henrik23mr.crud_bookstore_demo1.service.BookService;
import com.henrik23mr.crud_bookstore_demo1.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping
    public Optional<List<Book>> getAllBooks(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Book> getBookById(Long id){
        return service.findbyId(id);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book){
        return service.saveBook(book);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updateBook){
        return service.updateBook(id, updateBook);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id){
        service.deleteBook(id);

    }

}

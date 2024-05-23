package com.henrik23mr.crud_bookstore_demo1.controller;

import com.henrik23mr.crud_bookstore_demo1.controller.BookController;
import com.henrik23mr.crud_bookstore_demo1.entity.Book;
import com.henrik23mr.crud_bookstore_demo1.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BookControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }

    @Test
    public void testGetAllBooks() throws Exception {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "Book 1", "Author 1", "ISBN1"));
        books.add(new Book(2L, "Book 2", "Author 2", "ISBN2"));

        when(bookService.findAll()).thenReturn(Optional.of(books));

        mockMvc.perform(get("/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Book 1"))
                .andExpect(jsonPath("$[1].title").value("Book 2"));
    }

    @Test
    public void testGetBookById() {
        Book book = new Book(1L, "Book 1", "Author 1", "ISBN1");

        when(bookService.findbyId(1L)).thenReturn(Optional.of(book));

        Optional<Book> result = bookController.getBookById(1L);

        assertEquals(Optional.of(book), result);
    }

    @Test
    public void testCreateBook() throws Exception {
        Book book = new Book(1L, "Book 1", "Author 1", "ISBN1");

        when(bookService.saveBook(any())).thenReturn(book);

        mockMvc.perform(post("/books")
                        .content("{ \"title\": \"Book 1\", \"author\": \"Author 1\", \"isbn\": \"ISBN1\" }")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Book 1"));
    }

    @Test
    public void testUpdateBook() throws Exception {
        Book updatedBook = new Book(1L, "Updated Book", "Updated Author", "Updated ISBN");

        when(bookService.updateBook(eq(1L), any())).thenReturn(updatedBook);

        mockMvc.perform(put("/books/{id}", 1L)
                        .content("{ \"title\": \"Updated Book\", \"author\": \"Updated Author\", \"isbn\": \"Updated ISBN\" }")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Book"));
    }

    @Test
    public void testDeleteBookById() throws Exception {
        mockMvc.perform(delete("/books/{id}", 1L))
                .andExpect(status().isOk());

        verify(bookService, times(1)).deleteBook(1L);
    }
}

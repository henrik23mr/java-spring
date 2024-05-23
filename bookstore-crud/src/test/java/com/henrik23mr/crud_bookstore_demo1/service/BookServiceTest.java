package com.henrik23mr.crud_bookstore_demo1.service;

import com.henrik23mr.crud_bookstore_demo1.entity.Book;
import com.henrik23mr.crud_bookstore_demo1.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository repository;

    @InjectMocks
    private BookService service;

    @Test
    public void testFindAll() {
        Book book1 = new Book(1L, "Book 1", "Author 1", "ISBN1");
        Book book2 = new Book(2L, "Book 2", "Author 2", "ISBN2");
        List<Book> books = Arrays.asList(book1, book2);

        when(repository.findAll()).thenReturn(books);

        Optional<List<Book>> result = service.findAll();

        assertEquals(Optional.of(books), result);
    }

    @Test
    public void testFindById() {
        Book book = new Book(1L, "Book 1", "Author 1", "ISBN1");

        when(repository.findBookById(1L)).thenReturn(Optional.of(book));

        Optional<Book> result = service.findbyId(1L);

        assertEquals(Optional.of(book), result);
    }

    @Test
    public void testSaveBook() {
        Book book = new Book(1L, "Book 1", "Author 1", "ISBN1");

        when(repository.save(book)).thenReturn(book);

        Book savedBook = service.saveBook(book);

        assertEquals(book, savedBook);
    }

    @Test
    public void testDeleteBook() {
        service.deleteBook(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdateBook() {
        Book existingBook = new Book(1L, "Book 1", "Author 1", "ISBN1");
        Book updatedBook = new Book(1L, "Updated Book", "Updated Author", "Updated ISBN");

        when(repository.findById(1L)).thenReturn(Optional.of(existingBook));
        when(repository.save(existingBook)).thenReturn(updatedBook);

        Book result = service.updateBook(1L, updatedBook);

        assertEquals(updatedBook, result);
        assertEquals(updatedBook.getTitle(), result.getTitle());
        assertEquals(updatedBook.getAuthor(), result.getAuthor());
        assertEquals(updatedBook.getIsbn(), result.getIsbn());
    }
}

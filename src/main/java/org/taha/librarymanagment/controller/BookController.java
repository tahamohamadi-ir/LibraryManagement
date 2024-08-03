package org.taha.librarymanagment.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taha.librarymanagment.model.dto.BookDto;
import org.taha.librarymanagment.model.filter.BookFilterDto;
import org.taha.librarymanagment.service.book.BookService;

import java.util.List;
/**
 * Controller for handling book related requests.
 */
@Slf4j
@RestController("/api/v1/book")
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    /**
     * Constructor for BookController.
     *
     * @param bookService The service to handle book operations.
     */
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * Endpoint to get all books.
     *
     * @param bookDto The filter to apply to the books.
     * @return A ResponseEntity containing a list of BookDto objects and an HTTP status.
     */
    @GetMapping("/getAllBooks")
    public ResponseEntity<List<BookDto>> getAllBooks(BookFilterDto bookDto) {
        List<BookDto> books = bookService.getAllBooks(bookDto);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    /**
     * Endpoint to get all books in a pageable format.
     *
     * @param bookDto The filter to apply to the books.
     * @param page The page number to retrieve.
     * @param size The number of books per page.
     * @return A ResponseEntity containing a Page of BookDto objects and an HTTP status.
     */
    @GetMapping("/getAllBooksPageable")
    public ResponseEntity<Page<BookDto>> getAllBooks(BookFilterDto bookDto, @RequestParam int page, @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BookDto> books = bookService.getAllBooks(bookDto, pageable);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    /**
     * Endpoint to save a book.
     *
     * @param bookDto The details of the book to save.
     * @return A ResponseEntity containing the saved BookDto object and an HTTP status.
     */
    @PostMapping("/saveBook")
    public ResponseEntity<BookDto> saveBook(@RequestBody BookDto bookDto) {
        BookDto savedBook = bookService.saveBook(bookDto);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    /**
     * Endpoint to get a book by their ID.
     *
     * @param id The ID of the book to retrieve.
     * @return A ResponseEntity containing the BookDto object and an HTTP status.
     */
    @GetMapping("/findBook/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        BookDto book = bookService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
}
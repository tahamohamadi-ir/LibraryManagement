package org.taha.librarymanagment.service.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.taha.librarymanagment.model.dto.BookDto;
import org.taha.librarymanagment.model.filter.BookFilterDto;

import java.util.List;

/**
 * Service interface for managing books.
 */
public interface BookService {

    /**
     * Retrieves all books that match the given filter.
     * @param bookDto the filter to apply
     * @return a list of BookDto objects that match the filter
     */
    List<BookDto> getAllBooks(BookFilterDto bookDto);

    /**
     * Retrieves a page of books that match the given filter.
     * @param bookDto the filter to apply
     * @param pageable the pagination information
     * @return a page of BookDto objects that match the filter
     */
    Page<BookDto> getAllBooks(BookFilterDto bookDto, Pageable pageable);

    /**
     * Saves a new book to the database.
     * @param bookDto the book to save
     * @return the saved book
     */
    BookDto saveBook(BookDto bookDto);

    /**
     * Retrieves a book by its ID.
     * @param id the ID of the book to retrieve
     * @return the book with the given ID
     */
    BookDto getBookById(Long id);


}
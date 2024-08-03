package org.taha.librarymanagment.service.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.taha.librarymanagment.model.dto.BookDto;
import org.taha.librarymanagment.model.entity.Book;
import org.taha.librarymanagment.model.filter.BookFilterDto;
import org.taha.librarymanagment.model.mapper.BookMapper;
import org.taha.librarymanagment.repository.BookRepository;
import org.taha.librarymanagment.repository.specification.BookSpecification;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Service class for managing books.
 */
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    /**
     * Constructor for BookServiceImpl.
     *
     * @param bookRepository the repository for accessing book data
     * @param bookMapper the mapper for converting between DTOs and entities
     */
    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    /**
     * Retrieves all books that match the provided filter.
     *
     * @param bookDto the filter to apply
     * @return a list of matching books
     */
    @Override
    public List<BookDto> getAllBooks(BookFilterDto bookDto) {
        BookSpecification spec = new BookSpecification(bookDto);
        List<BookDto> list = new ArrayList<>();
        for (Book book : bookRepository.findAll(spec)) {
            BookDto dto = bookMapper.toDto(book);
            list.add(dto);
        }
        return list;
    }

    /**
     * Retrieves a page of books that match the provided filter.
     *
     * @param bookDto the filter to apply
     * @param pageable the pagination information
     * @return a page of matching books
     */
    @Override
    public Page<BookDto> getAllBooks(BookFilterDto bookDto, Pageable pageable) {
        BookSpecification spec = new BookSpecification(bookDto);
        return bookRepository.findAll(spec, pageable)
                .map(bookMapper::toDto);
    }

    /**
     * Saves a book.
     *
     * @param bookDto the book to save
     * @return the saved book
     */
    @Override
    public BookDto saveBook(BookDto bookDto) {
        return bookMapper.toDto(bookRepository.save(bookMapper.toEntity(bookDto)));
    }

    /**
     * Retrieves a book by its ID.
     *
     * @param id the ID of the book to retrieve
     * @return the retrieved book
     * @throws NoSuchElementException if no book with the provided ID could be found
     */
    @Override
    public BookDto getBookById(Long id) {
        return bookMapper.toDto(bookRepository.findById(id).orElseThrow());
    }

}
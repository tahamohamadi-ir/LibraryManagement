package org.taha.librarymanagment.service.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.taha.librarymanagment.model.dto.BookDto;
import org.taha.librarymanagment.model.filter.BookFilterDto;
import org.taha.librarymanagment.model.mapper.BookMapper;
import org.taha.librarymanagment.repository.BookRepository;
import org.taha.librarymanagment.repository.specification.BookSpecification;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<BookDto> getAllBooks(BookFilterDto bookDto) {
        BookSpecification spec = new BookSpecification(bookDto);
        return bookRepository.findAll(spec)
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<BookDto> getAllBooks(BookFilterDto bookDto, Pageable pageable) {
        BookSpecification spec = new BookSpecification(bookDto);
        return bookRepository.findAll(spec, pageable)
                .map(bookMapper::toDto);
    }

    @Override
    public BookDto saveBook(BookDto bookDto) {
        return bookMapper.toDto(bookRepository.save(bookMapper.toEntity(bookDto)));
    }

    @Override
    public BookDto getBookById(Long id) {
        return bookMapper.toDto(bookRepository.findById(id).orElseThrow());
    }

}
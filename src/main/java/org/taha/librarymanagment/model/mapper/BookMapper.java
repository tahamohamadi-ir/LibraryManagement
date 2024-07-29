package org.taha.librarymanagment.model.mapper;

import org.mapstruct.*;
import org.taha.librarymanagment.model.dto.BookDto;
import org.taha.librarymanagment.model.entity.Book;
import org.taha.librarymanagment.model.filter.BookFilterDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {AuthorMapper.class, TranslatorMapper.class})
public interface BookMapper {
    Book toEntity(BookDto bookDto);

    BookDto toDto(Book book);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Book partialUpdate(BookDto bookDto, @MappingTarget Book book);



}
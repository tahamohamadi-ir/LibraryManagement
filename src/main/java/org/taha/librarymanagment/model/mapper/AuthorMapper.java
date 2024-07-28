package org.taha.librarymanagment.model.mapper;

import org.mapstruct.*;
import org.taha.librarymanagment.model.dto.AuthorDto;
import org.taha.librarymanagment.model.entity.Author;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {PersonMapper.class})
public interface AuthorMapper {
    Author toEntity(AuthorDto authorDto);

    AuthorDto toDto(Author author);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Author partialUpdate(AuthorDto authorDto, @MappingTarget Author author);
}
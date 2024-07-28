package org.taha.librarymanagment.model.mapper;

import org.mapstruct.*;
import org.taha.librarymanagment.model.dto.TranslatorDto;
import org.taha.librarymanagment.model.entity.Translator;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {PersonMapper.class})
public interface TranslatorMapper {
    Translator toEntity(TranslatorDto translatorDto);

    TranslatorDto toDto(Translator translator);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Translator partialUpdate(TranslatorDto translatorDto, @MappingTarget Translator translator);
}
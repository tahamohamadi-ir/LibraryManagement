package org.taha.librarymanagment.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.taha.librarymanagment.model.dto.PersonDto;
import org.taha.librarymanagment.model.entity.Person;
import org.taha.librarymanagment.model.filter.PersonFilterDto;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonDto toDto(Person person);

    Person toEntity(PersonDto personDto);

    @Mapping(target = "firstName", source = "firstName")
    @Mapping(target = "lastName", source = "lastName")
    @Mapping(target = "gender", source = "gender")
    @Mapping(target = "birthDate", source = "birthDate")
    @Mapping(target = "nationalities", source = "nationalities")
    PersonFilterDto toFilterDto(PersonDto personDto);
}
package org.taha.librarymanagment.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.taha.librarymanagment.model.dto.MemberDto;
import org.taha.librarymanagment.model.entity.Member;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MemberMapper {
    Member toEntity(MemberDto memberDto);
    MemberDto toDto(Member member);
}

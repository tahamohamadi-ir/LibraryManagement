package org.taha.librarymanagment.model.mapper;

import org.mapstruct.*;
import org.taha.librarymanagment.model.dto.BorrowListDto;
import org.taha.librarymanagment.model.entity.BorrowList;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {BookMapper.class, MemberMapper.class})
public interface BorrowListMapper {
    BorrowList toEntity(BorrowListDto borrowListDto);

    BorrowListDto toDto(BorrowList borrowList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    BorrowList partialUpdate(BorrowListDto borrowListDto, @MappingTarget BorrowList borrowList);
}
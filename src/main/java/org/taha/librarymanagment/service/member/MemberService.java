package org.taha.librarymanagment.service.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.taha.librarymanagment.model.dto.MemberDto;
import org.taha.librarymanagment.model.filter.MemberFilter;

import java.util.Date;
import java.util.List;

public interface MemberService {

    MemberDto save(MemberDto memberDto);
    MemberDto update(MemberDto memberDto);
    MemberDto findById(Long id);
    MemberDto findByNationalCode(String nationalCode);
    MemberDto findByFilter(MemberFilter filter);

    List<MemberDto> findAll();
    Page<MemberDto> findAll(Pageable pageable);
    List<MemberDto> findAllActive();
    Page<MemberDto> findAllActive(Pageable pageable);

    List<MemberDto> findByRegistrationDateAndName(MemberFilter filter);
    Page<MemberDto> findByRegistrationDateAndName(MemberFilter filter, Pageable pageable);


}

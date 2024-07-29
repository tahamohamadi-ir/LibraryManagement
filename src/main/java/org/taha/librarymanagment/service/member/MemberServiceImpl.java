package org.taha.librarymanagment.service.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.taha.librarymanagment.model.dto.MemberDto;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {


    @Override
    public Page<MemberDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<MemberDto> findAll() {
        return List.of();
    }

    @Override
    public MemberDto findById(Long id) {
        return null;
    }

    @Override
    public MemberDto save(MemberDto memberDto) {
        return null;
    }

    @Override
    public MemberDto update(Long id, MemberDto memberDto) {
        return null;
    }
}

package org.taha.librarymanagment.service.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.taha.librarymanagment.model.dto.MemberDto;
import org.taha.librarymanagment.model.entity.Member;
import org.taha.librarymanagment.model.filter.MemberFilterDto;
import org.taha.librarymanagment.model.mapper.MemberMapper;
import org.taha.librarymanagment.repository.MemberRepository;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public MemberServiceImpl(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    @Override
    public Page<MemberDto> findAllByFilter(MemberFilterDto memberFilterDto, Pageable pageable) {
        return null;
    }

    @Override
    public List<MemberDto> findAll(MemberFilterDto memberFilterDto) {
        return List.of();
    }

    @Override
    public MemberDto findById(Long id) {
        return memberRepository.findById(id)
                .map(memberMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + id));
    }

    @Override
    public MemberDto save(MemberDto memberDto) {
        return memberMapper.toDto(memberRepository.save(memberMapper.toEntity(memberDto)));
    }

    @Override
    public int update(Long id, MemberDto memberDto) {
        return memberRepository.updateMember(memberDto.getPhoneNumber(), memberDto.getAddress(), memberDto.getMembershipStatus(), memberDto.getEmail(), memberDto.getNationalCode(), memberDto.getFatherName(), id);
    }
}

package org.taha.librarymanagment.service.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.taha.librarymanagment.model.dto.MemberDto;
import org.taha.librarymanagment.model.entity.Member;
import org.taha.librarymanagment.model.enumeration.MembershipStatusEnum;
import org.taha.librarymanagment.model.filter.MemberFilterDto;
import org.taha.librarymanagment.model.mapper.MemberMapper;
import org.taha.librarymanagment.repository.MemberRepository;
import org.taha.librarymanagment.repository.specification.MemberSpecification;

import java.util.List;

/**
 * Service implementation for managing members.
 */
@Service
@Slf4j
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    /**
     * Constructor for MemberServiceImpl.
     *
     * @param memberRepository the member repository
     * @param memberMapper the member mapper
     */
    public MemberServiceImpl(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    /**
     * Creates a specification for filtering members.
     *
     * @param memberFilterDto the member filter DTO
     * @return the member specification
     */
    private MemberSpecification createSpecification(MemberFilterDto memberFilterDto) {
        return new MemberSpecification(memberFilterDto);
    }

    /**
     * Finds all members by filter with pagination.
     *
     * @param memberFilterDto the member filter DTO
     * @param pageable the pagination information
     * @return a page of member DTOs
     */
    @Override
    public Page<MemberDto> findAllByFilter(MemberFilterDto memberFilterDto, Pageable pageable) {
        MemberSpecification spec = createSpecification(memberFilterDto);
        Page<Member> memberPage = memberRepository.findAll(spec, pageable);
        List<MemberDto> memberDtos = memberPage.map(memberMapper::toDto).getContent();
        return new PageImpl<>(memberDtos, pageable, memberPage.getTotalElements());
    }

    /**
     * Finds all members by filter.
     *
     * @param memberFilterDto the member filter DTO
     * @return a list of member DTOs
     */
    @Override
    public List<MemberDto> findAllDto(MemberFilterDto memberFilterDto) {
        MemberSpecification spec = createSpecification(memberFilterDto);
        List<Member> members = memberRepository.findAll(spec);
        return members.stream()
                .map(memberMapper::toDto)
                .toList();
    }

    /**
     * Finds a member by ID.
     *
     * @param id the member ID
     * @return the member DTO
     * @throws ResponseStatusException if the member is not found
     */
    @Override
    public MemberDto findById(Long id) {
        return memberRepository.findById(id)
                .map(memberMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found with id: " + id));
    }


    /**
     * Saves a new member.
     *
     * @param memberDto the member DTO
     * @return the saved member DTO
     */
    @Override
    public MemberDto save(MemberDto memberDto) {
        return memberMapper.toDto(memberRepository.save(memberMapper.toEntity(memberDto)));
    }

    /**
     * Updates a member's details.
     *
     * @param id the member ID
     * @param memberDto the member DTO
     * @return the number of entities updated
     * @throws ResponseStatusException if the member ID or details are null, or if the member is not found, or if the update fails
     */
    @Override
    @Transactional
    public int update(Long id, MemberDto memberDto) {
        if (id == null || memberDto == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Member ID and details must not be null");
        }

        if (!memberRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found with id: " + id);
        }

        try {
            return memberRepository.updateMember(
                    memberDto.getPhoneNumber(),
                    memberDto.getAddress(),
                    memberDto.getMembershipStatus(),
                    memberDto.getEmail(),
                    memberDto.getNationalCode(),
                    memberDto.getFatherName(),
                    id
            );
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update member details", e);
        }
    }
    /**
     * Activates a member by updating their membership status to Active.
     *
     * @param id the member's ID
     * @return the number of entities updated
     */
    @Override
    public int activateMember(Long id) {
        return memberRepository.updateMembershipStatusById(MembershipStatusEnum.Active, id);
    }

    /**
     * Deactivates a member by updating their membership status to Inactive.
     *
     * @param id the member's ID
     * @return the number of entities updated
     */
    @Override
    public int deactivateMember(Long id) {
        return memberRepository.updateMembershipStatusById(MembershipStatusEnum.Inactive, id);
    }

    /**
     * Suspends a member by updating their membership status to Suspended.
     *
     * @param id the member's ID
     * @return the number of entities updated
     */
    @Override
    public int suspendMember(Long id) {
        return memberRepository.updateMembershipStatusById(MembershipStatusEnum.Suspended, id);
    }

    /**
     * Expires a member by updating their membership status to Expired.
     *
     * @param id the member's ID
     * @return the number of entities updated
     */
    @Override
    public int expireMember(Long id) {
        return memberRepository.updateMembershipStatusById(MembershipStatusEnum.Expired, id);
    }

    /**
     * Sets a member's membership status to InProgress.
     *
     * @param id the member's ID
     * @return the number of entities updated
     */
    @Override
    public int inProgressMember(Long id) {
        return memberRepository.updateMembershipStatusById(MembershipStatusEnum.InProgress, id);
    }
}
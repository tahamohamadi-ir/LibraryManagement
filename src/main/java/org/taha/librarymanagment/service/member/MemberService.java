package org.taha.librarymanagment.service.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.taha.librarymanagment.model.dto.MemberDto;
import org.taha.librarymanagment.model.filter.MemberFilterDto;

import java.util.List;

/**
 * MemberService is an interface that defines the contract for the member service.
 * It provides methods to perform CRUD operations on MemberDto objects.
 */
public interface MemberService {

    /**
     * Retrieves all MemberDto objects in a paginated format.
     *
     * @param pageable the pagination information
     * @return a page of MemberDto objects
     */
    Page<MemberDto> findAllByFilter(MemberFilterDto memberFilterDto, Pageable pageable);

    /**
     * Retrieves all MemberDto objects.
     *
     * @return a list of MemberDto objects
     */
    List<MemberDto> findAll(MemberFilterDto memberFilterDto);

    /**
     * Retrieves a MemberDto object by its ID.
     *
     * @param id the ID of the MemberDto object
     * @return the MemberDto object with the given ID
     */
    MemberDto findById(Long id);

    /**
     * Saves a new MemberDto object.
     *
     * @param memberDto the MemberDto object to save
     * @return the saved MemberDto object
     */
    MemberDto save(MemberDto memberDto);

    /**
     * Updates an existing MemberDto object.
     *
     * @param id the ID of the MemberDto object to update
     * @param memberDto the MemberDto object with updated information
     * @return the updated MemberDto object
     */
    int update(Long id, MemberDto memberDto);


    int activateMember(Long id);
    int deactivateMember(Long id);
    int suspendMember(Long id);
    int expireMember(Long id);
    int inProgressMember(Long id);




}
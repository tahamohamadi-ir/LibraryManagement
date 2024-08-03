package org.taha.librarymanagment.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taha.librarymanagment.model.dto.MemberDto;
import org.taha.librarymanagment.model.filter.MemberFilterDto;
import org.taha.librarymanagment.service.member.MemberService;

import java.util.List;
/**
 * Controller for handling Member related requests.
 */
@RestController("/api/v1/member")
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    /**
     * Constructor for MemberController.
     *
     * @param memberService The service to handle member operations.
     */
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * Endpoint to get all members.
     *
     * @param memberFilterDto The filter to apply to the members.
     * @return A ResponseEntity containing a list of MemberDto objects and an HTTP status.
     */
    @PostMapping("/findMembers")
    public ResponseEntity<List<MemberDto>> getAllMembers(@RequestBody MemberFilterDto memberFilterDto){
        List<MemberDto> members = memberService.findAllDto(memberFilterDto);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    /**
     * Endpoint to get all members in a pageable format.
     *
     * @param memberFilterDto The filter to apply to the members.
     * @param page The page number to retrieve.
     * @param size The number of members per page.
     * @return A ResponseEntity containing a Page of MemberDto objects and an HTTP status.
     */
    @PostMapping("/findMembersPageable")
    public ResponseEntity<Page<MemberDto>> getAllMembersPageable(@RequestBody MemberFilterDto memberFilterDto, @RequestParam int page, @RequestParam int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<MemberDto> members = memberService.findAllByFilter(memberFilterDto, pageable);
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    /**
     * Endpoint to get a member by their ID.
     *
     * @param id The ID of the member to retrieve.
     * @return A ResponseEntity containing the MemberDto object and an HTTP status.
     */
    @GetMapping("/findMember/{id}")
    public ResponseEntity<MemberDto> getMemberById(@PathVariable Long id) {
        MemberDto member = memberService.findById(id);
        return new ResponseEntity<>(member, HttpStatus.OK);
    }

    /**
     * Endpoint to create a new member.
     *
     * @param memberDto The details of the member to create.
     * @return A ResponseEntity containing the created MemberDto object and an HTTP status.
     */
    @PostMapping("/createMember")
    public ResponseEntity<MemberDto> createMember(@RequestBody MemberDto memberDto) {
        MemberDto createdMember = memberService.save(memberDto);
        return new ResponseEntity<>(createdMember, HttpStatus.CREATED);
    }

    /**
     * Endpoint to update a member.
     *
     * @param id The ID of the member to update.
     * @param memberDto The new details of the member.
     * @return A ResponseEntity with an HTTP status.
     */
    @PutMapping("/updateMember/{id}")
    public ResponseEntity<Void> updateMember(@PathVariable Long id, @RequestBody MemberDto memberDto) {
        memberService.update(id, memberDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Endpoint to activate a member.
     *
     * @param id The ID of the member to activate.
     * @return A ResponseEntity with an HTTP status.
     */
    @PutMapping("/activateMemver/{id}")
    public ResponseEntity<Void> activateMember(@PathVariable Long id) {
        memberService.activateMember(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Endpoint to deactivate a member.
     *
     * @param id The ID of the member to deactivate.
     * @return A ResponseEntity with an HTTP status.
     */
    @PutMapping("/deactivateMember/{id}")
    public ResponseEntity<Void> deactivateMember(@PathVariable Long id) {
        memberService.deactivateMember(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Endpoint to suspend a member.
     *
     * @param id The ID of the member to suspend.
     * @return A ResponseEntity with an HTTP status.
     */
    @PutMapping("/suspendMember/{id}")
    public ResponseEntity<Void> suspendMember(@PathVariable Long id) {
        memberService.suspendMember(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Endpoint to expire a member.
     *
     * @param id The ID of the member to expire.
     * @return A ResponseEntity with an HTTP status.
     */
    @PutMapping("/expireMember/{id}")
    public ResponseEntity<Void> expireMember(@PathVariable Long id) {
        memberService.expireMember(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Endpoint to set a member's status to in progress.
     *
     * @param id The ID of the member to set to in progress.
     * @return A ResponseEntity with an HTTP status.
     */
    @PutMapping("/inprogressMember/{id}")
    public ResponseEntity<Void> inProgressMember(@PathVariable Long id) {
        memberService.inProgressMember(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
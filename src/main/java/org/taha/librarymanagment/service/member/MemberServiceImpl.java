package org.taha.librarymanagment.service.member;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.taha.librarymanagment.model.dto.MemberDto;
import org.taha.librarymanagment.model.entity.Member;
import org.taha.librarymanagment.model.enumeration.MembershipStatusEnum;
import org.taha.librarymanagment.model.filter.MemberFilter;
import org.taha.librarymanagment.model.mapper.MemberMapper;
import org.taha.librarymanagment.repository.MemberRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    private static final String MEMBERSHIP_STATUS = "membershipStatus";
    private static final String NATIONAL_CODE = "nationalCode";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String FATHER_NAME = "fatherName";
    private static final String PHONE_NUMBER = "phoneNumber";
    private static final String GENDER = "gender";
    private static final String EMAIL = "email";
    private static final String BIRTH_DATE = "birthDate";
    private static final String REGISTER_DATE = "registerDate";

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public MemberServiceImpl(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    @Override
    public MemberDto save(MemberDto memberDto) {
        Member member = memberMapper.toEntity(memberDto);
        member = memberRepository.save(member);
        return memberMapper.toDto(member);
    }

    @Override
    public MemberDto update(MemberDto memberDto) {
        Member member = memberMapper.toEntity(memberDto);
        member = memberRepository.save(member);
        return memberMapper.toDto(member);
    }

    @Override
    public MemberDto findById(Long id) {
        return memberRepository.findById(id)
                .map(memberMapper::toDto)
                .orElse(null);
    }

    @Override
    public MemberDto findByNationalCode(String nationalCode) {
        return memberRepository.findOne((root, query, cb) -> cb.equal(root.get(NATIONAL_CODE), nationalCode))
                .map(memberMapper::toDto)
                .orElse(null);
    }

    @Override
    public MemberDto findByFilter(MemberFilter filter) {
        Specification<Member> spec = createSpecification(filter);
        return memberRepository.findOne(spec)
                .map(memberMapper::toDto)
                .orElse(null);
    }

    @Override
    public List<MemberDto> findAll() {
        return memberRepository.findAll().stream()
                .map(memberMapper::toDto)
                .toList();
    }

    @Override
    public Page<MemberDto> findAll(Pageable pageable) {
        return memberRepository.findAll(pageable)
                .map(memberMapper::toDto);
    }

    @Override
    public List<MemberDto> findAllActive() {
        return memberRepository.findAll((root, query, cb) -> cb.equal(root.get(MEMBERSHIP_STATUS), MembershipStatusEnum.Active))
                .stream()
                .map(memberMapper::toDto)
                .toList();
    }

    @Override
    public Page<MemberDto> findAllActive(Pageable pageable) {
        return memberRepository.findAll((root, query, cb) -> cb.equal(root.get(MEMBERSHIP_STATUS), MembershipStatusEnum.Active), pageable)
                .map(memberMapper::toDto);
    }

    @Override
    public List<MemberDto> findByRegistrationDateAndName(MemberFilter filter) {
        Specification<Member> spec = createSpecification(filter);
        return memberRepository.findAll(spec).stream()
                .map(memberMapper::toDto)
                .toList();
    }

    @Override
    public Page<MemberDto> findByRegistrationDateAndName(MemberFilter filter, Pageable pageable) {
        Specification<Member> spec = createSpecification(filter);
        Page<Member> members = memberRepository.findAll(spec, pageable);
        return members.map(memberMapper::toDto);
    }

    private Specification<Member> createSpecification(MemberFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            addFirstNamePredicate(filter, root, cb, predicates);
            addLastNamePredicate(filter, root, cb, predicates);
            addNationalCodePredicate(filter, root, cb, predicates);
            addFatherNamePredicate(filter, root, cb, predicates);
            addPhoneNumberPredicate(filter, root, cb, predicates);
            addGenderPredicate(filter, root, cb, predicates);
            addMembershipStatusPredicate(filter, root, cb, predicates);
            addEmailPredicate(filter, root, cb, predicates);
            addBirthDatePredicates(filter, root, cb, predicates);
            addRegisterDatePredicates(filter, root, cb, predicates);

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private void addFirstNamePredicate(MemberFilter filter, Root<Member> root, CriteriaBuilder cb, List<Predicate> predicates) {
        if (filter.getFirstName() != null && !filter.getFirstName().isEmpty()) {
            predicates.add(cb.like(root.get(FIRST_NAME), "%" + filter.getFirstName() + "%"));
        }
    }

    private void addLastNamePredicate(MemberFilter filter, Root<Member> root, CriteriaBuilder cb, List<Predicate> predicates) {
        if (filter.getLastName() != null && !filter.getLastName().isEmpty()) {
            predicates.add(cb.like(root.get(LAST_NAME), "%" + filter.getLastName() + "%"));
        }
    }

    private void addNationalCodePredicate(MemberFilter filter, Root<Member> root, CriteriaBuilder cb, List<Predicate> predicates) {
        if (filter.getNationalCode() != null && !filter.getNationalCode().isEmpty()) {
            predicates.add(cb.equal(root.get(NATIONAL_CODE), filter.getNationalCode()));
        }
    }

    private void addFatherNamePredicate(MemberFilter filter, Root<Member> root, CriteriaBuilder cb, List<Predicate> predicates) {
        if (filter.getFatherName() != null && !filter.getFatherName().isEmpty()) {
            predicates.add(cb.like(root.get(FATHER_NAME), "%" + filter.getFatherName() + "%"));
        }
    }

    private void addPhoneNumberPredicate(MemberFilter filter, Root<Member> root, CriteriaBuilder cb, List<Predicate> predicates) {
        if (filter.getPhoneNumber() != null && !filter.getPhoneNumber().isEmpty()) {
            predicates.add(cb.equal(root.get(PHONE_NUMBER), filter.getPhoneNumber()));
        }
    }

    private void addGenderPredicate(MemberFilter filter, Root<Member> root, CriteriaBuilder cb, List<Predicate> predicates) {
        if (filter.getGender() != null) {
            predicates.add(cb.equal(root.get(GENDER), filter.getGender()));
        }
    }

    private void addMembershipStatusPredicate(MemberFilter filter, Root<Member> root, CriteriaBuilder cb, List<Predicate> predicates) {
        if (filter.getMembershipStatus() != null) {
            predicates.add(cb.equal(root.get(MEMBERSHIP_STATUS), filter.getMembershipStatus()));
        }
    }

    private void addEmailPredicate(MemberFilter filter, Root<Member> root, CriteriaBuilder cb, List<Predicate> predicates) {
        if (filter.getEmail() != null && !filter.getEmail().isEmpty()) {
            predicates.add(cb.equal(root.get(EMAIL), filter.getEmail()));
        }
    }

    private void addBirthDatePredicates(MemberFilter filter, Root<Member> root, CriteriaBuilder cb, List<Predicate> predicates) {
        if (filter.getBirthDateFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get(BIRTH_DATE), filter.getBirthDateFrom()));
        }
        if (filter.getBirthDateThru() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get(BIRTH_DATE), filter.getBirthDateThru()));
        }
    }

    private void addRegisterDatePredicates(MemberFilter filter, Root<Member> root, CriteriaBuilder cb, List<Predicate> predicates) {
        if (filter.getRegisterDateFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get(REGISTER_DATE), filter.getRegisterDateFrom()));
        }
        if (filter.getRegisterDateThru() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get(REGISTER_DATE), filter.getRegisterDateThru()));
        }
    }
}
package org.taha.librarymanagment.repository.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.taha.librarymanagment.model.entity.Member;
import org.taha.librarymanagment.model.filter.MemberFilterDto;

import java.util.ArrayList;
import java.util.List;

public class MemberSpecification implements Specification<Member> {

    private final MemberFilterDto filterDto;

    public MemberSpecification(MemberFilterDto filterDto) {
        this.filterDto = filterDto;
    }

    @Override
    public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (filterDto.getFirstName() != null) {
            predicates.add(cb.like(cb.upper(root.get("person").get("firstName")), "%" + filterDto.getFirstName().toUpperCase() + "%"));
        }

        if (filterDto.getLastName() != null) {
            predicates.add(cb.like(cb.upper(root.get("person").get("lastName")), "%" + filterDto.getLastName().toUpperCase() + "%"));
        }

        if (filterDto.getGender() != null) {
            predicates.add(cb.equal(root.get("person").get("gender"), filterDto.getGender()));
        }

        if (filterDto.getBirthDateFrom() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("person").get("birthDate"), filterDto.getBirthDateFrom()));
        }

        if (filterDto.getBirthDateThru() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("person").get("birthDate"), filterDto.getBirthDateThru()));
        }

        if (filterDto.getNationalities() != null) {
            predicates.add(cb.equal(root.get("person").get("nationalities"), filterDto.getNationalities()));
        }

        if (filterDto.getPhoneNumber() != null) {
            predicates.add(cb.like(cb.upper(root.get("phoneNumber")), "%" + filterDto.getPhoneNumber().toUpperCase() + "%"));
        }

        if (filterDto.getMembershipStatus() != null) {
            predicates.add(cb.equal(root.get("membershipStatus"), filterDto.getMembershipStatus()));
        }

        if (filterDto.getEmail() != null) {
            predicates.add(cb.like(cb.upper(root.get("email")), "%" + filterDto.getEmail().toUpperCase() + "%"));
        }

        if (filterDto.getRegisterDateFrom() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("registerDate"), filterDto.getRegisterDateFrom()));
        }

        if (filterDto.getRegisterDateThru() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("registerDate"), filterDto.getRegisterDateThru()));
        }

        if (filterDto.getNationalCode() != null) {
            predicates.add(cb.like(cb.upper(root.get("nationalCode")), "%" + filterDto.getNationalCode().toUpperCase() + "%"));
        }

        if (filterDto.getFatherName() != null) {
            predicates.add(cb.like(cb.upper(root.get("fatherName")), "%" + filterDto.getFatherName().toUpperCase() + "%"));
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }

    public static Specification<Member> hasId(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }
}
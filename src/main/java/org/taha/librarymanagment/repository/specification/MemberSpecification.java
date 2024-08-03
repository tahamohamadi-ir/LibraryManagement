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

/**
 * Specification for querying {@link Member} entities based on filters.
 * This class implements the {@link Specification} interface and overrides its toPredicate method to provide custom query logic.
 */
public class MemberSpecification implements Specification<Member> {

    private final MemberFilterDto filterDto;

    /**
     * Constructs a new MemberSpecification with the provided filter data transfer object.
     *
     * @param filterDto the filter data transfer object containing the query parameters
     */
    public MemberSpecification(MemberFilterDto filterDto) {
        this.filterDto = filterDto;
    }

    /**
     * Converts the filter data transfer object into a {@link Predicate} for querying {@link Member} entities.
     * This method is called internally by the JPA provider to form the WHERE clause of the query.
     *
     * @param root the root type in the from clause, used to form the predicate
     * @param query the criteria query object, used to form the predicate
     * @param cb the criteria builder, used to form the predicate
     * @return a {@link Predicate} formed from the filter data transfer object
     */
    @Override
    public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        // Initialize a list to hold all the predicates for the query
        List<Predicate> predicates = new ArrayList<>();

// If the first name filter is not null, add a predicate for the first name
        if (filterDto.getFirstName() != null) {
            predicates.add(cb.like(cb.upper(root.get("person").get("firstName")), "%" + filterDto.getFirstName().toUpperCase() + "%"));
        }

// If the last name filter is not null, add a predicate for the last name
        if (filterDto.getLastName() != null) {
            predicates.add(cb.like(cb.upper(root.get("person").get("lastName")), "%" + filterDto.getLastName().toUpperCase() + "%"));
        }

// If the gender filter is not null, add a predicate for the gender
        if (filterDto.getGender() != null) {
            predicates.add(cb.equal(root.get("person").get("gender"), filterDto.getGender()));
        }

// If the birth date from filter is not null, add a predicate for the birth date
        if (filterDto.getBirthDateFrom() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("person").get("birthDate"), filterDto.getBirthDateFrom()));
        }

// If the birth date thru filter is not null, add a predicate for the birth date
        if (filterDto.getBirthDateThru() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("person").get("birthDate"), filterDto.getBirthDateThru()));
        }

// If the nationalities filter is not null, add a predicate for the nationalities
        if (filterDto.getNationalities() != null) {
            predicates.add(cb.equal(root.get("person").get("nationalities"), filterDto.getNationalities()));
        }

// If the phone number filter is not null, add a predicate for the phone number
        if (filterDto.getPhoneNumber() != null) {
            predicates.add(cb.like(cb.upper(root.get("phoneNumber")), "%" + filterDto.getPhoneNumber().toUpperCase() + "%"));
        }

// If the membership status filter is not null, add a predicate for the membership status
        if (filterDto.getMembershipStatus() != null) {
            predicates.add(cb.equal(root.get("membershipStatus"), filterDto.getMembershipStatus()));
        }

// If the email filter is not null, add a predicate for the email
        if (filterDto.getEmail() != null) {
            predicates.add(cb.like(cb.upper(root.get("email")), "%" + filterDto.getEmail().toUpperCase() + "%"));
        }

// If the register date from filter is not null, add a predicate for the register date
        if (filterDto.getRegisterDateFrom() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("registerDate"), filterDto.getRegisterDateFrom()));
        }

// If the register date thru filter is not null, add a predicate for the register date
        if (filterDto.getRegisterDateThru() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("registerDate"), filterDto.getRegisterDateThru()));
        }

// If the national code filter is not null, add a predicate for the national code
        if (filterDto.getNationalCode() != null) {
            predicates.add(cb.like(cb.upper(root.get("nationalCode")), "%" + filterDto.getNationalCode().toUpperCase() + "%"));
        }

// If the father name filter is not null, add a predicate for the father name
        if (filterDto.getFatherName() != null) {
            predicates.add(cb.like(cb.upper(root.get("fatherName")), "%" + filterDto.getFatherName().toUpperCase() + "%"));
        }

// Return the conjunction of all predicates
        return cb.and(predicates.toArray(new Predicate[0]));
        }

    /**
     * Creates a specification for querying {@link Member} entities by id.
     *
     * @param id the id of the member
     * @return a {@link Specification} for querying members by id
     */
    public static Specification<Member> hasId(Long id) {
        return (root, query, cb) -> cb.equal(root.get("id"), id);
    }}
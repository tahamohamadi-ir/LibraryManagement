
package org.taha.librarymanagment.repository.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.taha.librarymanagment.model.entity.Person;
import org.taha.librarymanagment.model.enumeration.GenderEnum;
import org.taha.librarymanagment.model.enumeration.NationalityEnum;
import org.taha.librarymanagment.model.filter.PersonFilterDto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PersonSpecification implements Specification<Person> {

    private final PersonFilterDto filterDto;

    public PersonSpecification(PersonFilterDto filterDto) {
        this.filterDto = filterDto;
    }

    @Override
    public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (filterDto.getFirstName() != null) {
            predicates.add(cb.like(cb.upper(root.get("firstName")), "%" + filterDto.getFirstName().toUpperCase() + "%"));
        }

        if (filterDto.getLastName() != null) {
            predicates.add(cb.like(cb.upper(root.get("lastName")), "%" + filterDto.getLastName().toUpperCase() + "%"));
        }

        if (filterDto.getGender() != null) {
            predicates.add(cb.equal(root.get("gender"), filterDto.getGender()));
        }

        if (filterDto.getBirthDate() != null) {
            predicates.add(cb.equal(root.get("birthDate"), filterDto.getBirthDate()));
        }

        if (filterDto.getNationalities() != null) {
            predicates.add(cb.equal(root.get("nationalities"), filterDto.getNationalities()));
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }

    public static Specification<Person> hasFirstName(String firstName) {
        return (root, query, criteriaBuilder) ->
                firstName == null ? null : criteriaBuilder.equal(criteriaBuilder.upper(root.get("firstName")), firstName.toUpperCase());
    }

    public static Specification<Person> hasLastName(String lastName) {
        return (root, query, criteriaBuilder) ->
                lastName == null ? null : criteriaBuilder.equal(criteriaBuilder.upper(root.get("lastName")), lastName.toUpperCase());
    }

    public static Specification<Person> hasGender(GenderEnum gender) {
        return (root, query, criteriaBuilder) ->
                gender == null ? null : criteriaBuilder.equal(root.get("gender"), gender);
    }

    public static Specification<Person> hasBirthDate(Timestamp birthDate) {
        return (root, query, criteriaBuilder) ->
                birthDate == null ? null : criteriaBuilder.equal(root.get("birthDate"), birthDate);
    }

    public static Specification<Person> hasNationalities(NationalityEnum nationalities) {
        return (root, query, criteriaBuilder) ->
                nationalities == null ? null : criteriaBuilder.equal(root.get("nationalities"), nationalities);
    }
}
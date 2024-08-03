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

/**
 * Specification for querying {@link Person} entities based on filters.
 * This class implements the {@link Specification} interface and overrides its toPredicate method to provide custom query logic.
 */
public class PersonSpecification implements Specification<Person> {

    private final PersonFilterDto filterDto;

    /**
     * Constructs a new PersonSpecification with the provided filter data transfer object.
     *
     * @param filterDto the filter data transfer object containing the query parameters
     */
    public PersonSpecification(PersonFilterDto filterDto) {
        this.filterDto = filterDto;
    }

    /**
     * Converts the filter data transfer object into a {@link Predicate} for querying {@link Person} entities.
     * This method is called internally by the JPA provider to form the WHERE clause of the query.
     *
     * @param root the root type in the from clause, used to form the predicate
     * @param query the criteria query object, used to form the predicate
     * @param cb the criteria builder, used to form the predicate
     * @return a {@link Predicate} formed from the filter data transfer object
     */
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

    /**
     * Creates a specification for querying {@link Person} entities by first name.
     *
     * @param firstName the first name of the person
     * @return a {@link Specification} for querying persons by first name
     */
    public static Specification<Person> hasFirstName(String firstName) {
        return (root, query, criteriaBuilder) ->
                firstName == null ? null : criteriaBuilder.equal(criteriaBuilder.upper(root.get("firstName")), firstName.toUpperCase());
    }

    /**
     * Creates a specification for querying {@link Person} entities by last name.
     *
     * @param lastName the last name of the person
     * @return a {@link Specification} for querying persons by last name
     */
    public static Specification<Person> hasLastName(String lastName) {
        return (root, query, criteriaBuilder) ->
                lastName == null ? null : criteriaBuilder.equal(criteriaBuilder.upper(root.get("lastName")), lastName.toUpperCase());
    }

    /**
     * Creates a specification for querying {@link Person} entities by gender.
     *
     * @param gender the gender of the person
     * @return a {@link Specification} for querying persons by gender
     */
    public static Specification<Person> hasGender(GenderEnum gender) {
        return (root, query, criteriaBuilder) ->
                gender == null ? null : criteriaBuilder.equal(root.get("gender"), gender);
    }

    /**
     * Creates a specification for querying {@link Person} entities by birth date.
     *
     * @param birthDate the birth date of the person
     * @return a {@link Specification} for querying persons by birth date
     */
    public static Specification<Person> hasBirthDate(Timestamp birthDate) {
        return (root, query, criteriaBuilder) ->
                birthDate == null ? null : criteriaBuilder.equal(root.get("birthDate"), birthDate);
    }

    /**
     * Creates a specification for querying {@link Person} entities by nationalities.
     *
     * @param nationalities the nationalities of the person
     * @return a {@link Specification} for querying persons by nationalities
     */
    public static Specification<Person> hasNationalities(NationalityEnum nationalities) {
        return (root, query, criteriaBuilder) ->
                nationalities == null ? null : criteriaBuilder.equal(root.get("nationalities"), nationalities);
    }
}
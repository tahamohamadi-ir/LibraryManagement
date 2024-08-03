package org.taha.librarymanagment.repository.specification;

import org.springframework.data.jpa.domain.Specification;
import org.taha.librarymanagment.model.entity.Book;
import org.taha.librarymanagment.model.filter.BookFilterDto;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Specification for querying {@link Book} entities based on filters.
 * This class implements the {@link Specification} interface and overrides its toPredicate method to provide custom query logic.
 */
public class BookSpecification implements Specification<Book> {

    private final BookFilterDto filterDto;

    /**
     * Constructs a new BookSpecification with the provided filter data transfer object.
     *
     * @param filterDto the filter data transfer object containing the query parameters
     */
    public BookSpecification(BookFilterDto filterDto) {
        this.filterDto = filterDto;
    }

    /**
     * Converts the filter data transfer object into a {@link Predicate} for querying {@link Book} entities.
     * This method is called internally by the JPA provider to form the WHERE clause of the query.
     *
     * @param root the root type in the from clause, used to form the predicate
     * @param query the criteria query object, used to form the predicate
     * @param cb the criteria builder, used to form the predicate
     * @return a {@link Predicate} formed from the filter data transfer object
     */
    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        // If title is not null, add a predicate for title
        if (filterDto.getTitle() != null) {
            predicates.add(cb.like(cb.upper(root.get("title")), "%" + filterDto.getTitle().toUpperCase() + "%"));
        }

        // If author is not null, add a predicate for author
        if (filterDto.getAuthor() != null) {
            predicates.add(cb.like(cb.upper(root.get("author")), "%" + filterDto.getAuthor().toUpperCase() + "%"));
        }

        // If publicationDateFrom is not null, add a predicate for publicationDate
        if (filterDto.getPublicationDateFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("publicationDate"), filterDto.getPublicationDateFrom()));
        }

        // If publicationDateThru is not null, add a predicate for publicationDate
        if (filterDto.getPublicationDateThru() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("publicationDate"), filterDto.getPublicationDateThru()));
        }

        // If isbn is not null, add a predicate for isbn
        if (filterDto.getIsbn() != null) {
            predicates.add(cb.like(cb.upper(root.get("isbn")), "%" + filterDto.getIsbn().toUpperCase() + "%"));
        }

        // Return the conjunction of all predicates
        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
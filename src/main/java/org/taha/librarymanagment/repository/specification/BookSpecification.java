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

public class BookSpecification implements Specification<Book> {

    private final BookFilterDto filterDto;

    public BookSpecification(BookFilterDto filterDto) {
        this.filterDto = filterDto;
    }

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (filterDto.getTitle() != null) {
            predicates.add(cb.like(cb.upper(root.get("title")), "%" + filterDto.getTitle().toUpperCase() + "%"));
        }

        if (filterDto.getAuthor() != null) {
            predicates.add(cb.like(cb.upper(root.get("author")), "%" + filterDto.getAuthor().toUpperCase() + "%"));
        }

        if (filterDto.getPublicationDateFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("publicationDate"), filterDto.getPublicationDateFrom()));
        }

        if (filterDto.getPublicationDateThru() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("publicationDate"), filterDto.getPublicationDateThru()));
        }

        if (filterDto.getIsbn() != null) {
            predicates.add(cb.like(cb.upper(root.get("isbn")), "%" + filterDto.getIsbn().toUpperCase() + "%"));
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }
}
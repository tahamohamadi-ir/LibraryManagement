package org.taha.librarymanagment.repository;

import org.springframework.stereotype.Repository;
import org.taha.librarymanagment.model.entity.Book;

@Repository
public interface BookRepository extends BaseEntityRepository<Book> {
}
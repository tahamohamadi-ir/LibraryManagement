package org.taha.librarymanagment.model.entity;

import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "Book")
public class Book extends BaseEntity {
    private String title;
    private Long bookNumber;
    private List<String> author;
    private List<String> translators;
    private String isbn;
    private String publisher;
    private Timestamp publicationDate;
    private int publicationYear;
    private int pages;
    private String language;
    private String description  ;
}

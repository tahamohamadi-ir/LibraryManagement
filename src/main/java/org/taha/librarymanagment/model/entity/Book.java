package org.taha.librarymanagment.model.entity;

import jakarta.persistence.*;
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
public class Book{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String title;
    private Long bookNumber;
    @OneToMany
    private List<Author> author;
    @OneToMany
    private List<Translator> translators;
    private String isbn;
    private String publisher;
    private Timestamp publicationDate;
    private int publicationYear;
    private int pages;
    private String language;
    private String description  ;
}

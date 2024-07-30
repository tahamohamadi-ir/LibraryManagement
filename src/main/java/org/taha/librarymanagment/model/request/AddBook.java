package org.taha.librarymanagment.model.request;

import jakarta.persistence.OneToMany;
import lombok.*;
import org.taha.librarymanagment.model.entity.Author;
import org.taha.librarymanagment.model.entity.Translator;

import java.sql.Timestamp;
import java.util.List;
@Setter
@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddBook {
    private String title;
    private Long bookNumber;
    private List<Author> author;
    private List<Translator> translators;
    private String isbn;
    private String publisher;
    private Timestamp publicationDate;
    private int publicationYear;
    private int pages;
    private String language;
    private String description  ;
}

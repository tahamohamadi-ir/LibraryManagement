package org.taha.librarymanagment.model.dto;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

/**
 * DTO for {@link org.taha.librarymanagment.model.entity.Book}
 */
@Setter
@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDto implements Serializable {
    Long id;
    String title;
    Long bookNumber;
    List<AuthorDto> author;
    List<TranslatorDto> translators;
    String isbn;
    String publisher;
    Timestamp publicationDate;
    int publicationYear;
    int pages;
    String language;
    String description;
}
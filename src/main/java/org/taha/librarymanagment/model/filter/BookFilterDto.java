package org.taha.librarymanagment.model.filter;

import lombok.*;

import java.sql.Timestamp;

@Setter
@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookFilterDto {
    private String title;
    private String author;
    private Timestamp publicationDateFrom;
    private Timestamp publicationDateThru;
    private String isbn;
}
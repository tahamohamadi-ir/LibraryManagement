package org.taha.librarymanagment.model.filter;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class BookFilterDto {
    private String title;
    private String author;
    private Timestamp publicationDateFrom;
    private Timestamp publicationDateThru;
    private String isbn;
}
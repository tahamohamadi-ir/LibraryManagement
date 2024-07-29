package org.taha.librarymanagment.model.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link org.taha.librarymanagment.model.entity.Author}
 */
@Setter
@Getter
@RequiredArgsConstructor
public class AuthorDto implements Serializable {
    Long id;
    PersonDto person;
}
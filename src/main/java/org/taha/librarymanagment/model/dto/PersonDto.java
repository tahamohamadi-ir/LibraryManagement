package org.taha.librarymanagment.model.dto;

import lombok.*;
import org.taha.librarymanagment.model.enumeration.GenderEnum;
import org.taha.librarymanagment.model.enumeration.NationalityEnum;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO for {@link org.taha.librarymanagment.model.entity.Person}
 */
@Setter
@Getter
@RequiredArgsConstructor
public class PersonDto implements Serializable {
    Long id;
    String firstName;
    String lastName;
    GenderEnum gender;
    Timestamp birthDate;
    NationalityEnum nationalities;
}
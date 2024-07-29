package org.taha.librarymanagment.model.filter;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.taha.librarymanagment.model.enumeration.GenderEnum;
import org.taha.librarymanagment.model.enumeration.NationalityEnum;

import java.sql.Timestamp;

@Getter
@Setter
public class PersonFilterDto {
    private String firstName;
    private String lastName;
    private GenderEnum gender;
    private Timestamp birthDate;
    private NationalityEnum nationalities;
}
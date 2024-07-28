package org.taha.librarymanagment.model.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link org.taha.librarymanagment.model.entity.Translator}
 */
@Value
public class TranslatorDto implements Serializable {
    Long id;
    PersonDto person;
}
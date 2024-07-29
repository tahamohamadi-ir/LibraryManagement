package org.taha.librarymanagment.model.dto;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link org.taha.librarymanagment.model.entity.Translator}
 */
@Setter
@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TranslatorDto implements Serializable {
    Long id;
    PersonDto person;
}
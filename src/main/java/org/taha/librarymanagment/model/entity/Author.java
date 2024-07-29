package org.taha.librarymanagment.model.entity;

import jakarta.persistence.*;
import lombok.*;

/**
 * Represents an author in the library management system.
 * This class is a JPA entity and is mapped to the "Author" table in the database.
 * It uses Lombok annotations for boilerplate code reduction.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "Author")
public class Author{
    /**
     * The unique ID of the author.
     * It is generated automatically by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    /**
     * The person associated with this author.
     * It is a one-to-one relationship, mapped by the "person_id" column in the "Author" table.
     */
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;
}
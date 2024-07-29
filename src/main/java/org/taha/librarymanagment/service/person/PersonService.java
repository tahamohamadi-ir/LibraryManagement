package org.taha.librarymanagment.service.person;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.taha.librarymanagment.model.dto.PersonDto;

import java.util.List;

/**
 * PersonService is an interface that defines the contract for the Person service.
 * It provides methods to perform CRUD operations on Person entities.
 */
public interface PersonService {

    /**
     * Retrieves all Person entities.
     *
     * @param personDto The PersonDto object to filter the persons.
     * @return A list of PersonDto objects.
     */
    List<PersonDto> getAllPerson(PersonDto personDto);

    /**
     * Retrieves all Person entities with pagination.
     *
     * @param personDto The PersonDto object to filter the persons.
     * @param pageable The pagination information.
     * @return A page of PersonDto objects.
     */
    Page<PersonDto> getAllPerson(PersonDto personDto, Pageable pageable);

    /**
     * Saves a new Person entity.
     *
     * @param personDto The PersonDto object to be saved.
     * @return The saved PersonDto object.
     */
    PersonDto savePerson(PersonDto personDto);

    /**
     * Retrieves a Person entity by its ID.
     *
     * @param id The ID of the Person entity.
     * @return The PersonDto object.
     */
    PersonDto getPersonById(Long id);

    /**
     * Updates a Person entity.
     *
     * @param id The ID of the Person entity to be updated.
     * @param personDto The PersonDto object with the updated data.
     * @return The number of entities updated.
     */
    int updatePerson(Long id, PersonDto personDto);
}
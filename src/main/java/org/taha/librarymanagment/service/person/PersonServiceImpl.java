package org.taha.librarymanagment.service.person;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.taha.librarymanagment.model.dto.PersonDto;
import org.taha.librarymanagment.model.filter.PersonFilterDto;
import org.taha.librarymanagment.model.mapper.PersonMapper;
import org.taha.librarymanagment.repository.PersonRepository;
import org.taha.librarymanagment.repository.specification.PersonSpecification;

import java.util.List;

/**
 * This class provides the implementation for the PersonService interface.
 * It uses a PersonRepository for database operations and a PersonMapper for converting between Person and PersonDto objects.
 */
@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    /**
     * Constructor for PersonServiceImpl.
     * @param personRepository the repository used for database operations
     * @param personMapper the mapper used for converting between Person and PersonDto objects
     */
    public PersonServiceImpl(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    /**
     * Creates a specification for filtering persons.
     * @param personFilterDto the person filter DTO
     * @return the person specification
     */
    private PersonSpecification createSpecification(PersonFilterDto personFilterDto) {
        return new PersonSpecification(personFilterDto);
    }

    /**
     * Retrieves all persons that match the given filter.
     * @param personDto the filter to apply
     * @return a list of PersonDto objects that match the filter
     */
    @Override
    public List<PersonDto> getAllPerson(PersonDto personDto) {
        PersonFilterDto filterDto = personMapper.toFilterDto(personDto);
        PersonSpecification spec = createSpecification(filterDto);
        return personRepository.findAll(spec)
                .stream()
                .map(personMapper::toDto)
                .toList();
    }

    /**
     * Retrieves a page of persons that match the given filter.
     * @param personDto the filter to apply
     * @param pageable the pagination information
     * @return a page of PersonDto objects that match the filter
     */
    @Override
    public Page<PersonDto> getAllPerson(PersonDto personDto, Pageable pageable) {
        PersonFilterDto filterDto = personMapper.toFilterDto(personDto);
        PersonSpecification spec = createSpecification(filterDto);
        return personRepository.findAll(spec, pageable)
                .map(personMapper::toDto);
    }

    /**
     * Saves a new person to the database.
     * @param personDto the person to save
     * @return the saved person
     */
    @Override
    public PersonDto savePerson(PersonDto personDto) {
        return personMapper.toDto(personRepository.save(personMapper.toEntity(personDto)));
    }

    /**
     * Retrieves a person by their ID.
     * @param id the ID of the person to retrieve
     * @return the person with the given ID
     */
    @Override
    public PersonDto getPersonById(Long id) {
        return personMapper.toDto(personRepository.findById(id).orElseThrow());
    }

    /**
     * Updates a person in the database.
     * @param id the ID of the person to update
     * @param personDto the new data for the person
     * @return the number of entities updated
     */
    @Override
    public int updatePerson(Long id, PersonDto personDto) {
        return personRepository.updatePerson(personDto.getFirstName(), personDto.getLastName(), personDto.getGender(), personDto.getBirthDate(), personDto.getNationalities(), id);
    }
}
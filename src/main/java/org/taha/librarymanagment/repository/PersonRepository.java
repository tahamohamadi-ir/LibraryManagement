package org.taha.librarymanagment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;
import org.taha.librarymanagment.model.entity.Person;
import org.taha.librarymanagment.model.enumeration.GenderEnum;
import org.taha.librarymanagment.model.enumeration.NationalityEnum;

import java.sql.Timestamp;
import java.util.List;

/**
 * Repository interface for managing {@link Person} entities.
 * It extends JpaRepository and JpaSpecificationExecutor for standard CRUD operations and specification executor functionality.
 */
public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {

    /**
     * Updates a person's details in the database.
     * This method is transactional, meaning it will either complete successfully or roll back any changes if an error occurs.
     * It uses a custom query to update the person's details.
     *
     * @param firstName The new first name of the person. Can be null.
     * @param lastName The new last name of the person. Can be null.
     * @param gender The new gender of the person. Can be null.
     * @param birthDate The new birth date of the person. Can be null.
     * @param nationalities The new nationalities of the person. Can be null.
     * @param id The id of the person to update.
     * @return The number of entities updated. Should be 1 if the operation was successful, 0 otherwise.
     */
    @Transactional
    @Modifying
    @Query("""
            update Person p set p.firstName = :firstName, p.lastName = :lastName, p.gender = :gender, p.birthDate = :birthDate, p.nationalities = :nationalities
            where p.id = :id""")
    int updatePerson(@Nullable @Param("firstName") String firstName, @Nullable @Param("lastName") String lastName, @Nullable @Param("gender") GenderEnum gender, @Nullable @Param("birthDate") Timestamp birthDate, @Nullable @Param("nationalities") NationalityEnum nationalities, @Param("id") Long id);

    /**
     * Finds all persons by the provided filters.
     * It uses a custom query to find the persons.
     *
     * @param firstName The first name of the person. Can be null.
     * @param lastName The last name of the person. Can be null.
     * @param gender The gender of the person. Can be null.
     * @param birthDate The birth date of the person. Can be null.
     * @param nationalities The nationalities of the person. Can be null.
     * @return The list of persons that match the provided filters.
     */
    @Query("""
            select p from Person p
            where upper(p.firstName) = upper(:firstName) and upper(p.lastName) = upper(:lastName) and p.gender = :gender and p.birthDate = :birthDate and p.nationalities = :nationalities""")
    List<Person> findAllByFilterDto(@Param("firstName") @Nullable String firstName, @Param("lastName") @Nullable String lastName, @Param("gender") @Nullable GenderEnum gender, @Param("birthDate") @Nullable Timestamp birthDate, @Param("nationalities") @Nullable NationalityEnum nationalities);

    /**
     * Finds all persons by the provided filters and returns a pageable result.
     * It uses a custom query to find the persons.
     *
     * @param firstName The first name of the person. Can be null.
     * @param lastName The last name of the person. Can be null.
     * @param gender The gender of the person. Can be null.
     * @param birthDate The birth date of the person. Can be null.
     * @param nationalities The nationalities of the person. Can be null.
     * @param pageable The pagination information.
     * @return The pageable list of persons that match the provided filters.
     */
    @Query("""
            select p from Person p
            where upper(p.firstName) = upper(:firstName) and upper(p.lastName) = upper(:lastName) and p.gender = :gender and p.birthDate = :birthDate and p.nationalities = :nationalities""")
    Page<Person> findPageableByFilterDto(@Param("firstName") @Nullable String firstName, @Param("lastName") @Nullable String lastName, @Param("gender") @Nullable GenderEnum gender, @Param("birthDate") @Nullable Timestamp birthDate, @Param("nationalities") @Nullable NationalityEnum nationalities, Pageable pageable);
}
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

public interface PersonRepository extends JpaRepository<Person, Long>, JpaSpecificationExecutor<Person> {
    @Transactional
    @Modifying
    @Query("""
            update Person p set p.firstName = :firstName, p.lastName = :lastName, p.gender = :gender, p.birthDate = :birthDate, p.nationalities = :nationalities
            where p.id = :id""")
    int updatePerson(@Nullable @Param("firstName") String firstName, @Nullable @Param("lastName") String lastName, @Nullable @Param("gender") GenderEnum gender, @Nullable @Param("birthDate") Timestamp birthDate, @Nullable @Param("nationalities") NationalityEnum nationalities, @Param("id") Long id);

    @Query("""
            select p from Person p
            where upper(p.firstName) = upper(:firstName) and upper(p.lastName) = upper(:lastName) and p.gender = :gender and p.birthDate = :birthDate and p.nationalities = :nationalities""")
    List<Person> findAllByFilterDto(@Param("firstName") @Nullable String firstName, @Param("lastName") @Nullable String lastName, @Param("gender") @Nullable GenderEnum gender, @Param("birthDate") @Nullable Timestamp birthDate, @Param("nationalities") @Nullable NationalityEnum nationalities);

    @Query("""
            select p from Person p
            where upper(p.firstName) = upper(:firstName) and upper(p.lastName) = upper(:lastName) and p.gender = :gender and p.birthDate = :birthDate and p.nationalities = :nationalities""")
    Page<Person> findPageableByFilterDto(@Param("firstName") @Nullable String firstName, @Param("lastName") @Nullable String lastName, @Param("gender") @Nullable GenderEnum gender, @Param("birthDate") @Nullable Timestamp birthDate, @Param("nationalities") @Nullable NationalityEnum nationalities, Pageable pageable);
}
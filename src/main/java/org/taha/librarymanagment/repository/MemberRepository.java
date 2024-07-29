package org.taha.librarymanagment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.taha.librarymanagment.model.entity.Member;
import org.taha.librarymanagment.model.enumeration.GenderEnum;
import org.taha.librarymanagment.model.enumeration.MembershipStatusEnum;
import org.taha.librarymanagment.model.enumeration.NationalityEnum;

import java.sql.Timestamp;
import java.util.List;

/**
 * Repository interface for Member entity.
 * It extends JpaRepository and JpaSpecificationExecutor for standard CRUD operations and specification executor functionality.
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, JpaSpecificationExecutor<Member> {

    /**
     * Custom query to find all members by various filters.
     * All parameters are optional and used to filter the results.
     *
     * @param firstName Filter by first name (case insensitive).
     * @param lastName Filter by last name (case insensitive).
     * @param gender Filter by gender.
     * @param birthDate Filter by birth date (must be less than or equal to this value).
     * @param birthDate1 Filter by birth date (must be greater than or equal to this value).
     * @param nationalities Filter by nationalities.
     * @param phoneNumber Filter by phone number (case insensitive).
     * @param membershipStatus Filter by membership status.
     * @param email Filter by email (case insensitive).
     * @param registerDate Filter by register date (must be less than or equal to this value).
     * @param registerDate1 Filter by register date (must be greater than or equal to this value).
     * @param nationalCode Filter by national code (case insensitive).
     * @param fatherName Filter by father's name (case insensitive).
     * @return List of members that match the filters.
     */
    @Query("""
            select m from Member m
            where upper(m.person.firstName) like upper(concat('%', :firstName, '%')) and upper(m.person.lastName) like upper(concat('%', :lastName, '%')) and m.person.gender = :gender and m.person.birthDate <= :birthDate and m.person.birthDate >= :birthDate1 and m.person.nationalities = :nationalities and upper(m.phoneNumber) like upper(concat('%', :phoneNumber, '%')) and m.membershipStatus = :membershipStatus and upper(m.email) like upper(concat('%', :email, '%')) and m.registerDate <= :registerDate and m.registerDate >= :registerDate1 and upper(m.nationalCode) like upper(concat('%', :nationalCode, '%')) and upper(m.fatherName) like upper(concat('%', :fatherName, '%'))""")
    List<Member> findAllByFilterDto(@Param("firstName") @Nullable String firstName, @Param("lastName") @Nullable String lastName, @Param("gender") @Nullable GenderEnum gender, @Param("birthDate") @Nullable Timestamp birthDate, @Param("birthDate1") @Nullable Timestamp birthDate1, @Param("nationalities") @Nullable NationalityEnum nationalities, @Param("phoneNumber") @Nullable String phoneNumber, @Param("membershipStatus") @Nullable MembershipStatusEnum membershipStatus, @Param("email") @Nullable String email, @Param("registerDate") @Nullable Timestamp registerDate, @Param("registerDate1") @Nullable Timestamp registerDate1, @Param("nationalCode") @Nullable String nationalCode, @Param("fatherName") @Nullable String fatherName);

    /**
     * Custom query to find all members by various filters and return a pageable result.
     * All parameters are optional and used to filter the results.
     *
     * @param firstName Filter by first name (case insensitive).
     * @param lastName Filter by last name (case insensitive).
     * @param gender Filter by gender.
     * @param birthDate Filter by birth date (must be less than or equal to this value).
     * @param birthDate1 Filter by birth date (must be greater than or equal to this value).
     * @param nationalities Filter by nationalities.
     * @param phoneNumber Filter by phone number (case insensitive).
     * @param membershipStatus Filter by membership status.
     * @param email Filter by email (case insensitive).
     * @param registerDate Filter by register date (must be less than or equal to this value).
     * @param registerDate1 Filter by register date (must be greater than or equal to this value).
     * @param nationalCode Filter by national code (case insensitive).
     * @param fatherName Filter by father's name (case insensitive).
     * @param pageable Pageable object to get the data in pages.
     * @return Page of members that match the filters.
     */
    @Query("""
            select m from Member m
            where upper(m.person.firstName) like upper(concat('%', :firstName, '%')) and upper(m.person.lastName) like upper(concat('%', :lastName, '%')) and m.person.gender = :gender and m.person.birthDate <= :birthDate and m.person.birthDate >= :birthDate1 and m.person.nationalities = :nationalities and upper(m.phoneNumber) like upper(concat('%', :phoneNumber, '%')) and m.membershipStatus = :membershipStatus and upper(m.email) like upper(concat('%', :email, '%')) and m.registerDate <= :registerDate and m.registerDate >= :registerDate1 and upper(m.nationalCode) like upper(concat('%', :nationalCode, '%')) and upper(m.fatherName) like upper(concat('%', :fatherName, '%'))""")
    Page<Member> findPageableByFilterDto(@Param("firstName") @Nullable String firstName, @Param("lastName") @Nullable String lastName, @Param("gender") @Nullable GenderEnum gender, @Param("birthDate") @Nullable Timestamp birthDate, @Param("birthDate1") @Nullable Timestamp birthDate1, @Param("nationalities") @Nullable NationalityEnum nationalities, @Param("phoneNumber") @Nullable String phoneNumber, @Param("membershipStatus") @Nullable MembershipStatusEnum membershipStatus, @Param("email") @Nullable String email, @Param("registerDate") @Nullable Timestamp registerDate, @Param("registerDate1") @Nullable Timestamp registerDate1, @Param("nationalCode") @Nullable String nationalCode, @Param("fatherName") @Nullable String fatherName, Pageable pageable);

    /**
     * Custom query to count the number of members by gender, nationalities, and membership status.
     * All parameters are optional and used to filter the results.
     *
     * @param gender Filter by gender.
     * @param nationalities Filter by nationalities.
     * @param membershipStatus Filter by membership status.
     * @return Count of members that match the filters.
     */
    @Query("""
            select count(m) from Member m
            where m.person.gender = :gender and m.person.nationalities = :nationalities and m.membershipStatus = :membershipStatus""")
    long countByGenderAndNationalitiesAndMembershipStatus(@Param("gender") @Nullable GenderEnum gender, @Param("nationalities") @Nullable NationalityEnum nationalities, @Param("membershipStatus") @Nullable MembershipStatusEnum membershipStatus);

}
package org.taha.librarymanagment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taha.librarymanagment.model.entity.Member;
import org.taha.librarymanagment.model.enumeration.MembershipStatusEnum;

/**
 * Repository interface for managing {@link Member} entities.
 * It extends JpaRepository and JpaSpecificationExecutor for standard CRUD operations and specification executor functionality.
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, JpaSpecificationExecutor<Member> {

    /**
     * Updates a member's details in the database.
     * This method is transactional, meaning it will either complete successfully or roll back any changes if an error occurs.
     * It uses a custom query to update the member's details.
     *
     * @param phoneNumber The new phone number of the member. Can be null.
     * @param address The new address of the member. Can be null.
     * @param membershipStatus The new membership status of the member. Can be null.
     * @param email The new email of the member. Can be null.
     * @param nationalCode The new national code of the member. Can be null.
     * @param fatherName The new father's name of the member. Can be null.
     * @param id The id of the member to update.
     * @return The number of entities updated. Should be 1 if the operation was successful, 0 otherwise.
     */
    @Transactional
    @Modifying
    @Query("""
        update Member m set m.phoneNumber = :phoneNumber, m.address = :address, m.membershipStatus = :membershipStatus, m.email = :email, m.nationalCode = :nationalCode, m.fatherName = :fatherName
        where m.id = :id""")
    int updateMember(@Nullable @Param("phoneNumber") String phoneNumber, @Nullable @Param("address") String address, @Nullable @Param("membershipStatus") MembershipStatusEnum membershipStatus, @Nullable @Param("email") String email, @Nullable @Param("nationalCode") String nationalCode, @Nullable @Param("fatherName") String fatherName, @Param("id") Long id);

    /**
     * Updates the membership status of a member by their id.
     * This method is transactional, meaning it will either complete successfully or roll back any changes if an error occurs.
     * It uses a custom query to update the membership status.
     *
     * @param membershipStatus The new membership status of the member.
     * @param id The id of the member to update.
     * @return The number of entities updated. Should be 1 if the operation was successful, 0 otherwise.
     */
    @Transactional
    @Modifying
    @Query("update Member m set m.membershipStatus = :membershipStatus where m.id = :id")
    int updateMembershipStatusById(@Param("membershipStatus") MembershipStatusEnum membershipStatus, @Param("id") Long id);
}
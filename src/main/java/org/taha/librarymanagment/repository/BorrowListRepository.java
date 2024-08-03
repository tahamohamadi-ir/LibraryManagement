package org.taha.librarymanagment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.taha.librarymanagment.model.entity.BorrowList;

import java.sql.Timestamp;
import java.util.List;

/**
 * Repository interface for managing {@link BorrowList} entities.
 */
@Repository
public interface BorrowListRepository extends JpaRepository<BorrowList, Long>, JpaSpecificationExecutor<BorrowList> {

    /**
     * Counts the number of books not returned by a member.
     *
     * @param id         the member's id
     * @param isReturned the return status of the books
     * @return the count of books not returned
     */
    @Query("select count(b) from BorrowList b where b.member.id = ?1 and b.isReturned = ?2")
    long countMemberNotReturn(Long id, boolean isReturned);

    /**
     * Finds all borrow lists by book id and return status.
     *
     * @param bookId     the book's id
     * @param isReturned the return status of the books
     * @return the list of borrow lists
     */
    List<BorrowList> findByBookIdAndIsReturned(Long bookId, boolean isReturned);

    /**
     * Updates the return date and return status of a borrow list by id.
     *
     * @param returnDate the new return date
     * @param isReturned the new return status
     * @param id         the borrow list's id
     * @return the number of rows affected
     */
    @Transactional
    @Modifying
    @Query("update BorrowList b set b.returnDate = :returnDate, b.isReturned = :isReturned where b.id = :id")
    int updateReturnDateAndIsReturnedById(@Param("returnDate") Timestamp returnDate, @Param("isReturned") boolean isReturned, @Param("id") Long id);

    /**
     * Updates the extension status and extension count of a borrow list by id.
     *
     * @param isExtended the new extension status
     * @param extendCount the new extension count
     * @param id         the borrow list's id
     * @return the number of rows affected
     */
    @Transactional
    @Modifying
    @Query("update BorrowList b set b.isExtended = :isExtended, b.extendCount = :extendCount where b.id = :id")
    int updateIsExtendedAndExtendCountById(@Param("isExtended") boolean isExtended, @Param("extendCount") int extendCount, @Param("id") Long id);

    /**
     * Updates the penalty of a borrow list by id.
     *
     * @param penalty the new penalty
     * @param id      the borrow list's id
     * @return the number of rows affected
     */
    @Transactional
    @Modifying
    @Query("update BorrowList b set b.penalty = :penalty where b.id = :id")
    int updatePenaltyById(@Param("penalty") int penalty, @Param("id") Long id);

    /**
     * Finds all borrow lists by book id.
     *
     * @param id the book's id
     * @return the list of borrow lists
     */
    @Query("select b from BorrowList b where b.book.id = :id")
    List<BorrowList> findBorrowedBook(@Param("id") Long id);

    /**
     * Finds all borrow lists by member id.
     *
     * @param id the member's id
     * @return the list of borrow lists
     */
    @Query("select b from BorrowList b where b.member.id = :id")
    List<BorrowList> findBorrowedByMemberId(@Param("id") Long id);

    /**
     * Finds all borrow lists within a specific period.
     *
     * @param borrowDateFrom the start of the period
     * @param borrowDateThru the end of the period
     * @return the list of borrow lists
     */
    @Query("""
            select b from BorrowList b
            where b.borrowDate >= :borrowDate and b.borrowDate <= :borrowDate1
            order by b.borrowDate""")
    List<BorrowList> findBorrowedPeriod(@Param("borrowDate") @Nullable Timestamp borrowDateFrom, @Param("borrowDate1") @Nullable Timestamp borrowDateThru);

    /**
     * Finds all borrow lists by member id, book id and within a specific period.
     *
     * @param bookId         the book's id
     * @param memberId       the member's id
     * @param borrowDateFrom the start of the period
     * @param borrowDateThru the end of the period
     * @return the list of borrow lists
     */
    @Query("""
            select b from BorrowList b
            where b.book.id = :bookId and b.member.id = :memberId and b.borrowDate >= :borrowDateFrom and b.borrowDate <= :borrowDateThru""")
    List<BorrowList> findInPeriodByMemberAndBook(@Param("bookId") @Nullable Long bookId, @Param("memberId") @Nullable Long memberId, @Param("borrowDateFrom") @Nullable Timestamp borrowDateFrom, @Param("borrowDateThru") @Nullable Timestamp borrowDateThru);

}
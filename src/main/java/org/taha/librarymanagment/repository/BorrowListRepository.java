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

@Repository
public interface BorrowListRepository extends JpaRepository<BorrowList, Long>, JpaSpecificationExecutor<BorrowList> {
    @Query("select count(b) from BorrowList b where b.member.id = ?1 and b.isReturned = ?2")
    long countMemberNotReturn(Long id, boolean isReturned);

    List<BorrowList> bookMemberNotReturned(Long id, boolean isReturned);

    @Transactional
    @Modifying
    @Query("update BorrowList b set b.returnDate = :returnDate, b.isReturned = :isReturned where b.id = :id")
    int updateReturnDateAndIsReturnedById(@Param("returnDate") Timestamp returnDate, @Param("isReturned") boolean isReturned, @Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update BorrowList b set b.isExtended = :isExtended, b.extendCount = :extendCount where b.id = :id")
    int updateIsExtendedAndExtendCountById(@Param("isExtended") boolean isExtended, @Param("extendCount") int extendCount, @Param("id") Long id);

    @Transactional
    @Modifying
    @Query("update BorrowList b set b.penalty = :penalty where b.id = :id")
    int updatePenaltyById(@Param("penalty") int penalty, @Param("id") Long id);

    @Query("select b from BorrowList b where b.book.id = :id")
    List<BorrowList> findBorrowedBook(@Param("id") Long id);

    @Query("select b from BorrowList b where b.member.id = :id")
    List<BorrowList> findBorrowedByMemberId(@Param("id") Long id);

    @Query("""
            select b from BorrowList b
            where b.borrowDate >= :borrowDate and b.borrowDate <= :borrowDate1
            order by b.borrowDate""")
    List<BorrowList> findBorrowedPeriod(@Param("borrowDate") @Nullable Timestamp borrowDateFrom, @Param("borrowDate1") @Nullable Timestamp borrowDateThru);

    @Query("""
            select b from BorrowList b
            where b.book.id = :bookId and b.member.id = :id1 and b.borrowDate = :borrowDate and b.borrowDate = :borrowDate1""")
    List<BorrowList> findInPeriodByMemberAndBook(@Param("bookId") @Nullable Long bookId, @Param("id1") @Nullable Long id1, @Param("borrowDate") @Nullable Timestamp borrowDate, @Param("borrowDate1") @Nullable Timestamp borrowDate1);

}
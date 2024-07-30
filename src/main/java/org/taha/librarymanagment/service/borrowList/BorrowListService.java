package org.taha.librarymanagment.service.borrowList;

import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.taha.librarymanagment.model.dto.BorrowListDto;
import org.taha.librarymanagment.model.dto.MonthlyReportDto;
import org.taha.librarymanagment.model.entity.BorrowList;

import java.sql.Timestamp;
import java.util.List;

public interface BorrowListService {
    BorrowListDto makeBorrow(BorrowListDto borrowListDto);
    int returnBook(BorrowListDto borrowListDto);
    public int extendBorrow(BorrowListDto borrowListDto, int extendCount);
    long countMemberNotReturn(Long id, boolean isReturned);
    List<BorrowList> bookMemberNotReturned(Long id, boolean isReturned);
    int updateReturnDateAndIsReturnedById(Timestamp returnDate, boolean isReturned,Long id);
    int updatePenaltyById( int penalty, Long id);
    List<BorrowList> findBorrowedBook(Long id);
    List<BorrowList> findBorrowedByMemberId(Long id);
    List<BorrowList> findBorrowedPeriod(Timestamp borrowDateFrom,Timestamp borrowDateThru);
    List<BorrowList> findInPeriodByMemberAndBook(Long bookId,Long memberId,Timestamp borrowDateFrom,Timestamp borrowDateThru);
    List<MonthlyReportDto> getMonthlyReport(Timestamp borrowDateFrom,Timestamp borrowDateThru);
}

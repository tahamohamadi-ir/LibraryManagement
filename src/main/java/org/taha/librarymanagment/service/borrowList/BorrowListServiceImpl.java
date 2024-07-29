package org.taha.librarymanagment.service.borrowList;

import org.springframework.stereotype.Service;
import org.taha.librarymanagment.model.dto.BorrowListDto;
import org.taha.librarymanagment.model.entity.BorrowList;
import org.taha.librarymanagment.model.mapper.BorrowListMapper;
import org.taha.librarymanagment.repository.BorrowListRepository;

import java.sql.Timestamp;
import java.util.List;

@Service
public class BorrowListServiceImpl implements BorrowListService{
    private final BorrowListRepository borrowListRepository;
    private final BorrowListMapper borrowListMapper;

    public BorrowListServiceImpl(BorrowListRepository borrowListRepository, BorrowListMapper borrowListMapper) {
        this.borrowListRepository = borrowListRepository;
        this.borrowListMapper = borrowListMapper;
    }

    @Override
    public BorrowListDto makeBorrow(BorrowListDto borrowListDto) {
        if (countMemberNotReturn(borrowListDto.getMember().getId(), false) < 2) {
            return borrowListMapper.toDto(borrowListRepository.save(borrowListMapper.toEntity(borrowListDto)));
        } else {
            return null;
        }

    }

    @Override
    public int returnBook(BorrowListDto borrowListDto) {
        BorrowList borrowList = borrowListMapper.toEntity(borrowListDto);
        borrowList.setReturned(true);
        borrowList.setReturnDate(new Timestamp(System.currentTimeMillis()));
        return borrowListRepository.updateReturnDateAndIsReturnedById(borrowList.getReturnDate(), borrowList.isReturned(), borrowList.getId());
    }

    @Override
    public int extendBorrow(BorrowListDto borrowListDto, int extendCount) {
        BorrowList borrowList = borrowListMapper.toEntity(borrowListDto);
        return borrowListRepository.updateIsExtendedAndExtendCountById(borrowList.isExtended(), extendCount, borrowList.getId());
    }

    @Override
    public long countMemberNotReturn(Long id, boolean isReturned) {
        return borrowListRepository.countMemberNotReturn(id, isReturned);
    }

    @Override
    public List<BorrowList> bookMemberNotReturned(Long id, boolean isReturned) {
        return borrowListRepository.findByBookIdAndIsReturned(id, false);
    }

    @Override
    public int updateReturnDateAndIsReturnedById(Timestamp returnDate, boolean isReturned, Long id) {
        return borrowListRepository.updateReturnDateAndIsReturnedById((new Timestamp(System.currentTimeMillis())), true, id);
    }


    @Override
    public int updatePenaltyById(int penalty, Long id) {
        return borrowListRepository.updatePenaltyById(1, id);
    }

    @Override
    public List<BorrowList> findBorrowedBook(Long bookId) {
        return borrowListRepository.findBorrowedBook(bookId);
    }

    @Override
    public List<BorrowList> findBorrowedByMemberId(Long memberId) {
        return borrowListRepository.findBorrowedByMemberId(memberId);
    }

    @Override
    public List<BorrowList> findBorrowedPeriod(Timestamp borrowDateFrom, Timestamp borrowDateThru) {
        return borrowListRepository.findBorrowedPeriod(borrowDateFrom, borrowDateThru);
    }

    @Override
    public List<BorrowList> findInPeriodByMemberAndBook(Long bookId, Long memberId, Timestamp borrowDateFrom, Timestamp borrowDateThru) {
        return borrowListRepository.findInPeriodByMemberAndBook(bookId, memberId, borrowDateFrom, borrowDateThru);
    }
}

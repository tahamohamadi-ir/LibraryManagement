package org.taha.librarymanagment.service.borrowList;

import org.springframework.stereotype.Service;
import org.taha.librarymanagment.model.dto.BorrowListDto;
import org.taha.librarymanagment.model.dto.MonthlyReportDto;
import org.taha.librarymanagment.model.entity.BorrowList;
import org.taha.librarymanagment.model.mapper.BorrowListMapper;
import org.taha.librarymanagment.repository.BorrowListRepository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Service class for managing borrow lists.
 */
@Service
public class BorrowListServiceImpl implements BorrowListService{
    private final BorrowListRepository borrowListRepository;
    private final BorrowListMapper borrowListMapper;

    /**
     * Constructor for BorrowListServiceImpl.
     *
     * @param borrowListRepository the repository for borrow lists
     * @param borrowListMapper the mapper for converting between BorrowList and BorrowListDto
     */
    public BorrowListServiceImpl(BorrowListRepository borrowListRepository, BorrowListMapper borrowListMapper) {
        this.borrowListRepository = borrowListRepository;
        this.borrowListMapper = borrowListMapper;
    }

    /**
     * Makes a borrow if the member has less than 2 books not returned.
     *
     * @param borrowListDto the borrow list data transfer object
     * @return the saved borrow list data transfer object, or null if the member has 2 or more books not returned
     */
    @Override
    public BorrowListDto makeBorrow(BorrowListDto borrowListDto) {
        if (countMemberNotReturn(borrowListDto.getMember().getId(), false) < 2) {
            return borrowListMapper.toDto(borrowListRepository.save(borrowListMapper.toEntity(borrowListDto)));
        } else {
            return null;
        }
    }

    /**
     * Returns a book.
     *
     * @param borrowListDto the borrow list data transfer object
     * @return the number of updated rows
     */
    @Override
    public int returnBook(BorrowListDto borrowListDto) {
        BorrowList borrowList = borrowListMapper.toEntity(borrowListDto);
        borrowList.setReturned(true);
        borrowList.setReturnDate(new Timestamp(System.currentTimeMillis()));
        return borrowListRepository.updateReturnDateAndIsReturnedById(borrowList.getReturnDate(), borrowList.isReturned(), borrowList.getId());
    }

    /**
     * Extends a borrow.
     *
     * @param borrowListDto the borrow list data transfer object
     * @param extendCount the number of times the borrow is extended
     * @return the number of updated rows
     */
    @Override
    public int extendBorrow(BorrowListDto borrowListDto, int extendCount) {
        BorrowList borrowList = borrowListMapper.toEntity(borrowListDto);
        return borrowListRepository.updateIsExtendedAndExtendCountById(borrowList.isExtended(), extendCount, borrowList.getId());
    }

    /**
     * Counts the number of books a member has not returned.
     *
     * @param id the member's id
     * @param isReturned whether the book is returned
     * @return the number of books the member has not returned
     */
    @Override
    public long countMemberNotReturn(Long id, boolean isReturned) {
        return borrowListRepository.countMemberNotReturn(id, isReturned);
    }

    /**
     * Finds books a member has not returned.
     *
     * @param id the member's id
     * @param isReturned whether the book is returned
     * @return the list of books the member has not returned
     */
    @Override
    public List<BorrowList> bookMemberNotReturned(Long id, boolean isReturned) {
        return borrowListRepository.findByBookIdAndIsReturned(id, false);
    }

    /**
     * Updates the return date and whether the book is returned by id.
     *
     * @param returnDate the return date
     * @param isReturned whether the book is returned
     * @param id the id of the borrow list
     * @return the number of updated rows
     */
    @Override
    public int updateReturnDateAndIsReturnedById(Timestamp returnDate, boolean isReturned, Long id) {
        return borrowListRepository.updateReturnDateAndIsReturnedById((new Timestamp(System.currentTimeMillis())), true, id);
    }

    /**
     * Updates the penalty by id.
     *
     * @param penalty the penalty
     * @param id the id of the borrow list
     * @return the number of updated rows
     */
    @Override
    public int updatePenaltyById(int penalty, Long id) {
        return borrowListRepository.updatePenaltyById(1, id);
    }

    /**
     * Finds borrowed books by book id.
     *
     * @param bookId the book's id
     * @return the list of borrowed books
     */
    @Override
    public List<BorrowList> findBorrowedBook(Long bookId) {
        return borrowListRepository.findBorrowedBook(bookId);
    }

    /**
     * Finds borrowed books by member id.
     *
     * @param memberId the member's id
     * @return the list of borrowed books
     */
    @Override
    public List<BorrowList> findBorrowedByMemberId(Long memberId) {
        return borrowListRepository.findBorrowedByMemberId(memberId);
    }

    /**
     * Finds borrowed books in a period.
     *
     * @param borrowDateFrom the start date of the period
     * @param borrowDateThru the end date of the period
     * @return the list of borrowed books in the period
     */
    @Override
    public List<BorrowList> findBorrowedPeriod(Timestamp borrowDateFrom, Timestamp borrowDateThru) {
        return borrowListRepository.findBorrowedPeriod(borrowDateFrom, borrowDateThru);
    }

    /**
     * Finds borrowed books in a period by member and book.
     *
     * @param bookId the book's id
     * @param memberId the member's id
     * @param borrowDateFrom the start date of the period
     * @param borrowDateThru the end date of the period
     * @return the list of borrowed books in the period by the member and book
     */
    @Override
    public List<BorrowList> findInPeriodByMemberAndBook(Long bookId, Long memberId, Timestamp borrowDateFrom, Timestamp borrowDateThru) {
        return borrowListRepository.findInPeriodByMemberAndBook(bookId, memberId, borrowDateFrom, borrowDateThru);
    }

    @Override
    public List<MonthlyReportDto> getMonthlyReport(Timestamp borrowDateFrom, Timestamp borrowDateThru) {
        List<BorrowList> borrowedPerPeriod = borrowListRepository.findBorrowedPeriod(borrowDateFrom, borrowDateThru);
        return borrowedPerPeriod.stream().map(this::borrowListToMonthlyReportDto).toList();
    }

    MonthlyReportDto borrowListToMonthlyReportDto(BorrowList borrowList) {
        return MonthlyReportDto.builder()
                .firstName(borrowList.getMember().getPerson().getFirstName())
                .lastName(borrowList.getMember().getPerson().getLastName())
                .nationalCode(borrowList.getMember().getNationalCode())
                .title(borrowList.getBook().getTitle())
                .bookNumber(borrowList.getBook().getBookNumber())
                .author(borrowList.getBook().getAuthor().stream().map(author -> author.getPerson().getFirstName() + " " + author.getPerson().getLastName()).toList())
                .translators(borrowList.getBook().getTranslators().stream().map(translator -> translator.getPerson().getFirstName() + " " + translator.getPerson().getLastName()).toList())
                .build();
    }
}
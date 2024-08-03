package org.taha.librarymanagment.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.taha.librarymanagment.model.dto.BorrowListDto;
import org.taha.librarymanagment.model.dto.MonthlyReportDto;
import org.taha.librarymanagment.model.entity.BorrowList;
import org.taha.librarymanagment.service.borrowList.BorrowListService;

import java.sql.Timestamp;
import java.util.List;

/**
 * Controller for handling BorrowList related requests.
 */
@Slf4j
@RestController("/api/v1/borrowList")
@RequestMapping("/borrowList")
public class BorrowListController {
    private final BorrowListService borrowListService;

    /**
     * Constructor for BorrowListController.
     *
     * @param borrowListService The service to handle borrow list operations.
     */
    public BorrowListController(BorrowListService borrowListService) {
        this.borrowListService = borrowListService;
    }

    /**
     * Endpoint to make a borrow.
     *
     * @param borrowListDto The details of the borrow to make.
     * @return A ResponseEntity containing the created BorrowListDto object and an HTTP status.
     */
    @PostMapping("/makeBorrow")
    public ResponseEntity<BorrowListDto> makeBorrow(@RequestBody BorrowListDto borrowListDto) {
        BorrowListDto borrowList = borrowListService.makeBorrow(borrowListDto);
        return new ResponseEntity<>(borrowList, HttpStatus.CREATED);
    }

    /**
     * Endpoint to return a book.
     *
     * @param borrowListDto The details of the borrow to return.
     * @return A ResponseEntity with an HTTP status.
     */
    @PutMapping("/returnBook")
    public ResponseEntity<Void> returnBook(@RequestBody BorrowListDto borrowListDto) {
        borrowListService.returnBook(borrowListDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Endpoint to extend a borrow.
     *
     * @param borrowListDto The details of the borrow to extend.
     * @param extendCount The number of times to extend the borrow.
     * @return A ResponseEntity with an HTTP status.
     */
    @PutMapping("/extendBorrow/{extendCount}")
    public ResponseEntity<Void> extendBorrow(@RequestBody BorrowListDto borrowListDto, @PathVariable int extendCount) {
        borrowListService.extendBorrow(borrowListDto, extendCount);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Endpoint to get the count of books not returned by a member.
     *
     * @param id The id of the member.
     * @param isReturned Whether the book is returned.
     * @return A ResponseEntity containing the count and an HTTP status.
     */
    @GetMapping("/countMemberNotReturn/{id}/{isReturned}")
    public ResponseEntity<Long> countMemberNotReturn(@PathVariable Long id, @PathVariable boolean isReturned) {
        Long count = borrowListService.countMemberNotReturn(id, isReturned);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }

    /**
     * Endpoint to get the list of books not returned by a member.
     *
     * @param id The id of the member.
     * @param isReturned Whether the book is returned.
     * @return A ResponseEntity containing the list of BorrowListDto objects and an HTTP status.
     */
    @GetMapping("/bookMemberNotReturned/{id}/{isReturned}")
    public ResponseEntity<List<BorrowList>> bookMemberNotReturned(@PathVariable Long id, @PathVariable boolean isReturned) {
        List<BorrowList> borrowList = borrowListService.bookMemberNotReturned(id, isReturned);
        return new ResponseEntity<>(borrowList, HttpStatus.OK);
    }

    /**
     * Endpoint to get the monthly report of borrowed books.
     *
     * @param borrowDateFrom The start date of the period.
     * @param borrowDateThru The end date of the period.
     * @return A ResponseEntity containing the list of MonthlyReportDto objects and an HTTP status.
     */
    @GetMapping("/getMonthlyReport/{borrowDateFrom}/{borrowDateThru}")
    public ResponseEntity<List<MonthlyReportDto>> getMonthlyReport(@PathVariable Timestamp borrowDateFrom, @PathVariable Timestamp borrowDateThru) {
        List<MonthlyReportDto> report = borrowListService.getMonthlyReport(borrowDateFrom, borrowDateThru);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }
}
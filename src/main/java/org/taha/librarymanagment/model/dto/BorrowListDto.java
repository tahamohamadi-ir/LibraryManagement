package org.taha.librarymanagment.model.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.taha.librarymanagment.model.entity.Book;
import org.taha.librarymanagment.model.entity.Member;

import java.sql.Timestamp;

public class BorrowListDto {
    private Book book;
    private Member member;
    private Timestamp borrowDate;
    private Timestamp returnDate;
    private int dueDays;
    private boolean isReturned;
    private boolean isExtended;
    private int extendCount;
    private int penalty;
}

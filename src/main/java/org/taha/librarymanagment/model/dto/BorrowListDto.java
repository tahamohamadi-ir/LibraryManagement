package org.taha.librarymanagment.model.dto;

import lombok.Value;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO for {@link org.taha.librarymanagment.model.entity.BorrowList}
 */
@Value
public class BorrowListDto implements Serializable {
    Long id;
    BookDto book;
    MemberDto member;
    Timestamp borrowDate;
    Timestamp returnDate;
    int dueDays;
    boolean isReturned;
    boolean isExtended;
    int extendCount;
    int penalty;
}
package org.taha.librarymanagment.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "BorrowList")
public class BorrowList {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    private Timestamp borrowDate;
    private Timestamp returnDate;
    private int dueDays;
    private boolean isReturned;
    private boolean isExtended;
    private int extendCount;
    private int penalty;
}

package org.taha.librarymanagment.service.borrowList;

import org.taha.librarymanagment.model.dto.BorrowListDto;

public interface BorrowListService {
    BorrowListDto makeBorrow(BorrowListDto borrowListDto);
    BorrowListDto returnBook(BorrowListDto borrowListDto);
    BorrowListDto extendBorrow(BorrowListDto borrowListDto);



}

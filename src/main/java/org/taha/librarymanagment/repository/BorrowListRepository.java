package org.taha.librarymanagment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.taha.librarymanagment.model.entity.BorrowList;

@Repository
public interface BorrowListRepository extends JpaRepository<BorrowList, Long>, JpaSpecificationExecutor<BorrowList> {
}
package org.taha.librarymanagment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.taha.librarymanagment.model.entity.Member;

@Repository
public interface MemberRepo extends JpaRepository<Member, Long>, JpaSpecificationExecutor<Member> {
}

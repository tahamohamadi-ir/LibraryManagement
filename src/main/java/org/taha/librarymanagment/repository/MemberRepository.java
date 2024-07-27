package org.taha.librarymanagment.repository;

import org.springframework.stereotype.Repository;
import org.taha.librarymanagment.model.entity.Member;

@Repository
public interface MemberRepository extends BaseEntityRepository<Member> {
}
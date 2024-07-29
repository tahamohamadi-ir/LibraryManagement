package org.taha.librarymanagment.model.filter;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.taha.librarymanagment.model.enumeration.GenderEnum;
import org.taha.librarymanagment.model.enumeration.MembershipStatusEnum;
import org.taha.librarymanagment.model.enumeration.NationalityEnum;

import java.sql.Timestamp;

@RequiredArgsConstructor
@Data
public class MemberFilterDto {
    private String firstName;
    private String lastName;
    private GenderEnum gender;
    private Timestamp birthDate;
    private Timestamp birthDate1;
    private NationalityEnum nationalities;
    private String phoneNumber;
    private MembershipStatusEnum membershipStatus;
    private String email;
    private Timestamp registerDate;
    private Timestamp registerDate1;
    private String nationalCode;
    private String fatherName;
    private Pageable pageable;
}
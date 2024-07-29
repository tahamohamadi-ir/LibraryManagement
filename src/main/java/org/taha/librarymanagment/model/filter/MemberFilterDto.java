package org.taha.librarymanagment.model.filter;

import lombok.*;
import org.springframework.data.domain.Pageable;
import org.taha.librarymanagment.model.enumeration.GenderEnum;
import org.taha.librarymanagment.model.enumeration.MembershipStatusEnum;
import org.taha.librarymanagment.model.enumeration.NationalityEnum;

import java.sql.Timestamp;

@Setter
@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberFilterDto {
    private String firstName;
    private String lastName;
    private GenderEnum gender;
    private Timestamp birthDateFrom;
    private Timestamp birthDateThru;
    private NationalityEnum nationalities;
    private String phoneNumber;
    private MembershipStatusEnum membershipStatus;
    private String email;
    private Timestamp registerDateFrom;
    private Timestamp registerDateThru;
    private String nationalCode;
    private String fatherName;
    private Pageable pageable;
}
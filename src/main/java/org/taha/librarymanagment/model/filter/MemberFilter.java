package org.taha.librarymanagment.model.filter;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.taha.librarymanagment.model.enumeration.GenderEnum;
import org.taha.librarymanagment.model.enumeration.MembershipStatusEnum;

import java.sql.Timestamp;

@RequiredArgsConstructor
@Data
public class MemberFilter {
    private String firstName;
    private String lastName;
    private GenderEnum gender;
    private MembershipStatusEnum membershipStatus;
    private String email;
    private Timestamp birthDateFrom;
    private Timestamp birthDateThru;
    private Timestamp registerDateFrom;
    private Timestamp registerDateThru;
}

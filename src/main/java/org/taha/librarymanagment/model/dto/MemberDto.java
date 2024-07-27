package org.taha.librarymanagment.model.dto;


import org.taha.librarymanagment.model.enumeration.GenderEnum;
import org.taha.librarymanagment.model.enumeration.MembershipStatusEnum;

import java.sql.Timestamp;

public class MemberDto {
    private String firstName;
    private String lastName;
    private String nationalCode;
    private String fatherName;
    private String phoneNumber;
    private String address;
    private GenderEnum gender;
    private String genderDesc;
    private MembershipStatusEnum membershipStatus;
    private String membershipStatusDesc;
    private String email;
    private Timestamp birthDate;
    private Timestamp registerDate;
}

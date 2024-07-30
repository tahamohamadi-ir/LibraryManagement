package org.taha.librarymanagment.model.request;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.taha.librarymanagment.model.enumeration.MembershipStatusEnum;

import java.sql.Timestamp;

@Setter
@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterMember {
    private RegisterPerson person;
    private String phoneNumber;
    private String address;
    private MembershipStatusEnum membershipStatus;
    private String email;
    private String nationalCode;
    private String fatherName;
}

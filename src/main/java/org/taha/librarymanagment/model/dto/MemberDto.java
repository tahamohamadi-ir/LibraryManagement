package org.taha.librarymanagment.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.taha.librarymanagment.model.enumeration.MembershipStatusEnum;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * DTO for {@link org.taha.librarymanagment.model.entity.Member}
 */
@Setter
@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto implements Serializable {
    Long id;
    PersonDto person;
    String phoneNumber;
    String address;
    MembershipStatusEnum membershipStatus;
    @Email
    String email;
    Timestamp registerDate;
    @Size(min = 10, max = 10)
    String nationalCode;
    String fatherName;
}
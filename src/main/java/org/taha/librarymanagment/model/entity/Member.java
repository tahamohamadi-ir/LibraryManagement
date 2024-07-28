package org.taha.librarymanagment.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.taha.librarymanagment.model.enumeration.GenderEnum;
import org.taha.librarymanagment.model.enumeration.MembershipStatusEnum;

import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "Member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;
    @Column(nullable = false)
    private String phoneNumber;
    private String address;
    @Enumerated(EnumType.STRING)
    private MembershipStatusEnum membershipStatus;
    @Email
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp registerDate;
    @Column(nullable = false, unique = true)
    @Size(min = 10, max = 10)
    private String nationalCode;
    private String fatherName;
}

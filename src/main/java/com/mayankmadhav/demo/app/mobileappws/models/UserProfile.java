package com.mayankmadhav.demo.app.mobileappws.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class UserProfile {

    private LocalDate dateOfBirth;

    private String fatherName;

    private String motherName;

    private String nationality;

    @CollectionTable(name = "user_addresses", joinColumns = @JoinColumn(name = "user_id"))
    @ElementCollection
    private List<Address> address;


}
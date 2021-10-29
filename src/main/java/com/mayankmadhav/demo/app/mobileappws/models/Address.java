package com.mayankmadhav.demo.app.mobileappws.models;

import com.mayankmadhav.demo.app.mobileappws.constants.enums.AddressType;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString()
public class Address {

    @Lob
    private String addressLLine;

    private String phone;

    private String city;

    private String state;

    private String country;

    private String pincode;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;

}
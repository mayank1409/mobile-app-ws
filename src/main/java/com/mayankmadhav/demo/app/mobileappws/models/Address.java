package com.mayankmadhav.demo.app.mobileappws.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.mayankmadhav.demo.app.mobileappws.constants.enums.AddressType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "address")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "user")
public class Address {

	@Id
	@GeneratedValue
	private Long id;

	private long createDate;

	private long updateDate;

	@Lob
	private String addressLLine;
	
	private String phone;

	private String city;

	private String state;

	private String country;

	private String pincode;

	@Enumerated(EnumType.STRING)
	private AddressType addressType;

	@ManyToOne()
	@JoinColumn(name = "user_id")
	private Users user;
}

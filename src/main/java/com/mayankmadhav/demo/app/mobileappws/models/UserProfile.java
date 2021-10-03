package com.mayankmadhav.demo.app.mobileappws.models;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "user_profile")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "user")
@Data
public class UserProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private long createDate;

	private long updateDate;

	private LocalDate dateOfBirth;

	private String fatherName;

	private String motherName;

	private String nationality;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userProfile")
	private Users user;

}

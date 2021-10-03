package com.mayankmadhav.demo.app.mobileappws.controller.dtos;

import java.time.LocalDate;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CompleteUserProfileDTO {

	private String firstName;

	private String lastame;

	private String mobile;
	
	private String email;
	
	private RoleDTO role;
	
	private List<String> privileges;
	
	private LocalDate dateOfBirth;

	private String fatherName;

	private String motherName;

	private String nationality;

}
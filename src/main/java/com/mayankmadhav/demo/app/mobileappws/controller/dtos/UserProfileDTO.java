package com.mayankmadhav.demo.app.mobileappws.controller.dtos;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDTO {

	private String firstName;

	private String lastame;

	private String mobile;
	
	private String email;
	
	@NotNull(message = "dateOfBirth can't be blank")
	@Past(message = "Date Of Birth should be in past")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dateOfBirth;

	@NotBlank(message = "fatherName can't be blank")
	private String fatherName;

	@NotBlank(message = "motherName can't be blank")
	private String motherName;

	@NotBlank(message = "nationality can't be blank")
	private String nationality;
	
	@NotEmpty(message = "address is required")
	@Valid
	private List<AddressDTO> address;

	
}

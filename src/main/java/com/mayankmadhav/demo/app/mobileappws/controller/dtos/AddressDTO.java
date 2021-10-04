package com.mayankmadhav.demo.app.mobileappws.controller.dtos;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

	@NotBlank(message = "addressLine 1 is required!")
	private String addressLLine;

	@NotBlank(message = "city is required!")
	private String city;

	@NotBlank(message = "state is required!")
	private String state;

	@NotBlank(message = "country is required!")
	private String country;

	@NotBlank(message = "pincode is required!")
	private String pincode;

	@NotBlank(message = "phone is required!")
	private String phone;

	private String addressType;

}

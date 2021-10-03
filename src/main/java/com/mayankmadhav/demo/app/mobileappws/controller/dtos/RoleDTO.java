package com.mayankmadhav.demo.app.mobileappws.controller.dtos;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDTO {

	@NotBlank(message = "name can't be blank")
	private String name;
}

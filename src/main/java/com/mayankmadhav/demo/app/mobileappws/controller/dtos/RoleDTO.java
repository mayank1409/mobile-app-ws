package com.mayankmadhav.demo.app.mobileappws.controller.dtos;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class RoleDTO {

    @NotBlank(message = "name can't be blank")
    private String name;
}

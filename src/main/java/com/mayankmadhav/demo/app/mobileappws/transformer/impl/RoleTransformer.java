package com.mayankmadhav.demo.app.mobileappws.transformer.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.mayankmadhav.demo.app.mobileappws.constants.enums.Roles;
import com.mayankmadhav.demo.app.mobileappws.controller.dtos.PageDTO;
import com.mayankmadhav.demo.app.mobileappws.controller.dtos.RoleDTO;
import com.mayankmadhav.demo.app.mobileappws.models.Role;
import com.mayankmadhav.demo.app.mobileappws.transformer.GenericTransformer;

@Component
public class RoleTransformer implements GenericTransformer<Role, RoleDTO> {

	@Override
	public Role fromDTO(RoleDTO dto) {
		return Role.builder().createDate(System.currentTimeMillis()).name(Roles.valueOf(dto.getName())).build();
	}

	@Override
	public RoleDTO toDTO(Role entity) {
		return RoleDTO.builder().name(entity.getName().toString()).build();
	}

	@Override
	public PageDTO<RoleDTO> toPageDto(Page<Role> page) {
		return null;
	}

}

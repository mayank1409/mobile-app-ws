package com.mayankmadhav.demo.app.mobileappws.transformer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.mayankmadhav.demo.app.mobileappws.constants.enums.Roles;
import com.mayankmadhav.demo.app.mobileappws.controller.dtos.PageDTO;
import com.mayankmadhav.demo.app.mobileappws.controller.dtos.UserDTO;
import com.mayankmadhav.demo.app.mobileappws.models.Users;
import com.mayankmadhav.demo.app.mobileappws.service.business.IRoleService;
import com.mayankmadhav.demo.app.mobileappws.transformer.GenericTransformer;

@Component
public class UserTransfomer implements GenericTransformer<Users, UserDTO> {

	@Autowired
	private IRoleService roleService;

	@Override
	public Users fromDTO(UserDTO userDto) {
		return Users.builder().firstName(userDto.getFirstName()).createDate(System.currentTimeMillis())
				.lastName(userDto.getLastName()).mobile(userDto.getMobile())
				.role(roleService.findByName(Roles.DEFAULT_USER.toString())).email(userDto.getEmail()).build();
	}

	@Override
	public UserDTO toDTO(Users userEntity) {
		return UserDTO.builder().firstName(userEntity.getFirstName()).lastName(userEntity.getLastName())
				.email(userEntity.getEmail()).mobile(userEntity.getMobile()).build();
	}

	@Override
	public PageDTO<UserDTO> toPageDto(Page<Users> page) {
		PageDTO<UserDTO> pageUserDto = new PageDTO<UserDTO>();
		pageUserDto.setNumberOfElements(page.getNumberOfElements());
		pageUserDto.setSize(page.getSize());
		pageUserDto.setPageNumber(page.getNumber());
		pageUserDto.setTotalPages(page.getTotalPages());
		pageUserDto.setContent(setContent(page));
		return pageUserDto;
	}

}

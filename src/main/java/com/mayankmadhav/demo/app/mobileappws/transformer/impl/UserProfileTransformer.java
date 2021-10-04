package com.mayankmadhav.demo.app.mobileappws.transformer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.mayankmadhav.demo.app.mobileappws.controller.dtos.PageDTO;
import com.mayankmadhav.demo.app.mobileappws.controller.dtos.UserProfileDTO;
import com.mayankmadhav.demo.app.mobileappws.models.UserProfile;
import com.mayankmadhav.demo.app.mobileappws.models.Users;
import com.mayankmadhav.demo.app.mobileappws.transformer.GenericTransformer;

@Component
public class UserProfileTransformer implements GenericTransformer<UserProfile, UserProfileDTO> {

	@Autowired
	private AddressTransformer addressTransformer;

	@Override
	public UserProfile fromDTO(UserProfileDTO dto) {
		return UserProfile.builder().fatherName(dto.getFatherName()).motherName(dto.getMotherName())
				.dateOfBirth(dto.getDateOfBirth()).nationality(dto.getNationality())
				.createDate(System.currentTimeMillis()).build();
	}

	@Override
	public UserProfileDTO toDTO(UserProfile entity) {
		Users user = entity.getUser();
		return UserProfileDTO.builder().firstName(user.getFirstName()).lastame(user.getLastName())
				.email(user.getEmail()).mobile(user.getMobile()).fatherName(entity.getFatherName())
				.motherName(entity.getMotherName()).dateOfBirth(entity.getDateOfBirth())
				.nationality(entity.getNationality()).address(addressTransformer.toListDTO(user.getAddress())).build();
	}

	@Override
	public PageDTO<UserProfileDTO> toPageDto(Page<UserProfile> page) {
		return null;
	}

}

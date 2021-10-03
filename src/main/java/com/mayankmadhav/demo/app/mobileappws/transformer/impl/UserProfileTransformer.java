package com.mayankmadhav.demo.app.mobileappws.transformer.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.mayankmadhav.demo.app.mobileappws.controller.dtos.PageDTO;
import com.mayankmadhav.demo.app.mobileappws.controller.dtos.UserProfileDTO;
import com.mayankmadhav.demo.app.mobileappws.models.UserProfile;
import com.mayankmadhav.demo.app.mobileappws.transformer.GenericTransformer;

@Component
public class UserProfileTransformer implements GenericTransformer<UserProfile, UserProfileDTO> {

	@Override
	public UserProfile fromDTO(UserProfileDTO dto) {
		return UserProfile.builder().fatherName(dto.getFatherName()).motherName(dto.getMotherName())
				.dateOfBirth(dto.getDateOfBirth()).nationality(dto.getNationality())
				.createDate(System.currentTimeMillis()).build();
	}

	@Override
	public UserProfileDTO toDTO(UserProfile entity) {
		return UserProfileDTO.builder().firstName(entity.getUser().getFirstName())
				.lastame(entity.getUser().getLastName()).fatherName(entity.getFatherName())
				.motherName(entity.getMotherName()).dateOfBirth(entity.getDateOfBirth())
				.nationality(entity.getNationality()).mobile(entity.getUser().getMobile()).build();
	}

	@Override
	public PageDTO<UserProfileDTO> toPageDto(Page<UserProfile> page) {
		// TODO Auto-generated method stub
		return null;
	}

}

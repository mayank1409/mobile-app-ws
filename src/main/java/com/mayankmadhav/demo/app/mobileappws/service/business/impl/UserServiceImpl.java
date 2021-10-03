package com.mayankmadhav.demo.app.mobileappws.service.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.mayankmadhav.demo.app.mobileappws.controller.dtos.CompleteUserProfileDTO;
import com.mayankmadhav.demo.app.mobileappws.exceptions.EntityAlreadyExistsException;
import com.mayankmadhav.demo.app.mobileappws.models.Role;
import com.mayankmadhav.demo.app.mobileappws.models.UserProfile;
import com.mayankmadhav.demo.app.mobileappws.models.Users;
import com.mayankmadhav.demo.app.mobileappws.repository.IUserRepository;
import com.mayankmadhav.demo.app.mobileappws.service.AbstractService;
import com.mayankmadhav.demo.app.mobileappws.service.business.IRoleService;
import com.mayankmadhav.demo.app.mobileappws.service.business.IUserService;

@Service
public class UserServiceImpl extends AbstractService<Users, Long> implements IUserService {

	private IUserRepository userRepository;

	@Autowired
	public UserServiceImpl(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Autowired
	private IRoleService roleService;

	@Override
	protected JpaRepository<Users, Long> repository() {
		return userRepository;
	}

	@Override
	public Users findByUserId(String userId) {
		return userRepository.findByUserId(userId);
	}

	@Override
	public Users saveUser(CompleteUserProfileDTO userProfileDto, String userId) {

		Users user = userRepository.findByEmail(userProfileDto.getEmail());

		if (user != null) {
			throw new EntityAlreadyExistsException("Email already added");
		}

		user = userRepository.findByMobile(userProfileDto.getMobile());
		if (user != null) {
			throw new EntityAlreadyExistsException("Mobile number already added");
		}

		Users userDetails = userRepository.findByUserId(userId);

		if (userDetails == null) {
			userDetails = Users.builder().build();
		}

		userDetails = Users.builder().createDate(System.currentTimeMillis()).updateDate(System.currentTimeMillis())
				.firstName(userProfileDto.getFirstName()).lastName(userProfileDto.getLastame())
				.email(userProfileDto.getEmail()).mobile(userProfileDto.getMobile()).build();

		if (userDetails.getUserProfile() == null) {
			UserProfile profileDetails = UserProfile.builder().fatherName(userProfileDto.getFatherName())
					.motherName(userProfileDto.getMotherName()).nationality(userProfileDto.getNationality())
					.dateOfBirth(userProfileDto.getDateOfBirth()).build();
			userDetails.setUserProfile(profileDetails);
		}

		if (userDetails.getRole() == null) {
			Role role = Role.builder().build();
			role = roleService.addRole(userProfileDto.getRole());
			userDetails.setRole(role);
		}

		return save(userDetails);
	}

	@Override
	public Users findByUsername(String username) {
		Users byEmail = userRepository.findByEmail(username);
		return byEmail == null ? null : byEmail;
	}

}

package com.mayankmadhav.demo.app.mobileappws.service.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.mayankmadhav.demo.app.mobileappws.controller.dtos.UserProfileDTO;
import com.mayankmadhav.demo.app.mobileappws.exceptions.EntityNotFoundException;
import com.mayankmadhav.demo.app.mobileappws.models.UserProfile;
import com.mayankmadhav.demo.app.mobileappws.models.Users;
import com.mayankmadhav.demo.app.mobileappws.repository.IUserProfileRepository;
import com.mayankmadhav.demo.app.mobileappws.service.AbstractService;
import com.mayankmadhav.demo.app.mobileappws.service.business.IUserProfileService;
import com.mayankmadhav.demo.app.mobileappws.service.business.IUserService;
import com.mayankmadhav.demo.app.mobileappws.transformer.impl.UserProfileTransformer;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserProfileServiceImpl extends AbstractService<UserProfile, Long> implements IUserProfileService {

	@Autowired
	private IUserProfileRepository userProfileRepository;

	@Autowired
	private UserProfileTransformer userProfileTransformer;

	@Autowired
	private IUserService userService;

	@Override
	protected JpaRepository<UserProfile, Long> repository() {
		return userProfileRepository;
	}

	@Override
	public UserProfile completeProfile(UserProfileDTO userProfileDto, String username) {
		Users user = userService.findByUsername(username);
		if (user == null) {
			throw new EntityNotFoundException();
		}
		UserProfile userProfile = userProfileTransformer.fromDTO(userProfileDto);
		userProfile.setUser(user);
		userProfile = save(userProfile);
		user.setUserProfile(userProfile);
		userService.save(user);
		return userProfile;
	}

	@Override
	public UserProfile getProfile(String userId) {
		log.info("fetching user with id {}", userId);
		Users user = userService.findByUserId(userId);
		if (user == null) {
			log.warn("user not found");
			throw new EntityNotFoundException();
		}
		log.info("return user with details {} ", user);
		return user.getUserProfile() == null ? null : user.getUserProfile();
	}

}

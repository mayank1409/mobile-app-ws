package com.mayankmadhav.demo.app.mobileappws.service.business;

import com.mayankmadhav.demo.app.mobileappws.controller.dtos.UserProfileDTO;
import com.mayankmadhav.demo.app.mobileappws.models.UserProfile;

public interface IUserProfileService {

	public UserProfile completeProfile(UserProfileDTO userProfileDto, String userId);
	
	public UserProfile getProfile(String userId);

}

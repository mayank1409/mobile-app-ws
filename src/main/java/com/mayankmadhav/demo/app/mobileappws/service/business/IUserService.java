package com.mayankmadhav.demo.app.mobileappws.service.business;

import com.mayankmadhav.demo.app.mobileappws.controller.dtos.CompleteUserProfileDTO;
import com.mayankmadhav.demo.app.mobileappws.models.Users;
import com.mayankmadhav.demo.app.mobileappws.service.base.IGenericService;

public interface IUserService extends IGenericService<Users, Long> {

	public Users findByUserId(String userId);
	
	public Users saveUser(CompleteUserProfileDTO userProfileDto, String userId);
	
	public Users findByUsername(String username);

}

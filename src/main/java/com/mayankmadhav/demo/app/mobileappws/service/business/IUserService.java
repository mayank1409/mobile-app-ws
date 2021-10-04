package com.mayankmadhav.demo.app.mobileappws.service.business;

import com.mayankmadhav.demo.app.mobileappws.models.Users;
import com.mayankmadhav.demo.app.mobileappws.service.base.IGenericService;

public interface IUserService extends IGenericService<Users, Long> {

	public Users findByUsername(String username);

}

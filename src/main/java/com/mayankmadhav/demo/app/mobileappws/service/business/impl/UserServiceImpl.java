package com.mayankmadhav.demo.app.mobileappws.service.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.mayankmadhav.demo.app.mobileappws.models.Users;
import com.mayankmadhav.demo.app.mobileappws.repository.IUserRepository;
import com.mayankmadhav.demo.app.mobileappws.service.AbstractService;
import com.mayankmadhav.demo.app.mobileappws.service.business.IUserService;

@Service
public class UserServiceImpl extends AbstractService<Users, Long> implements IUserService {

	private IUserRepository userRepository;

	@Autowired
	public UserServiceImpl(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	protected JpaRepository<Users, Long> repository() {
		return userRepository;
	}

	@Override
	public Users findByUsername(String username) {
		Users byEmail = userRepository.findByEmail(username);
		return byEmail == null ? null : byEmail;
	}

}

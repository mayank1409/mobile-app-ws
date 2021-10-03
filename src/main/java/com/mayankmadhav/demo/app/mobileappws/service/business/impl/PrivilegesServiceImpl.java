package com.mayankmadhav.demo.app.mobileappws.service.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.mayankmadhav.demo.app.mobileappws.models.Privileges;
import com.mayankmadhav.demo.app.mobileappws.repository.IPrivilegesRepository;
import com.mayankmadhav.demo.app.mobileappws.service.AbstractService;
import com.mayankmadhav.demo.app.mobileappws.service.business.IPrivilegesService;

@Service
public class PrivilegesServiceImpl extends AbstractService<Privileges, Long> implements IPrivilegesService {

	@Autowired
	private IPrivilegesRepository privilegesRepository;

	@Override
	protected JpaRepository<Privileges, Long> repository() {
		return privilegesRepository;
	}

}

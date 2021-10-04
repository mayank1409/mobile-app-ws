package com.mayankmadhav.demo.app.mobileappws.service.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.mayankmadhav.demo.app.mobileappws.models.Address;
import com.mayankmadhav.demo.app.mobileappws.repository.IAddressRepository;
import com.mayankmadhav.demo.app.mobileappws.service.AbstractService;
import com.mayankmadhav.demo.app.mobileappws.service.business.IAddressService;

@Service
public class AddressServiceImpl extends AbstractService<Address, Long> implements IAddressService {

	@Autowired
	private IAddressRepository addressRepository;

	@Override
	protected JpaRepository<Address, Long> repository() {
		return addressRepository;
	}

}

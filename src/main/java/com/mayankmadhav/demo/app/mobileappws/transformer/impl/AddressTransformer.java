package com.mayankmadhav.demo.app.mobileappws.transformer.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.mayankmadhav.demo.app.mobileappws.constants.enums.AddressType;
import com.mayankmadhav.demo.app.mobileappws.controller.dtos.AddressDTO;
import com.mayankmadhav.demo.app.mobileappws.controller.dtos.PageDTO;
import com.mayankmadhav.demo.app.mobileappws.models.Address;
import com.mayankmadhav.demo.app.mobileappws.models.Users;
import com.mayankmadhav.demo.app.mobileappws.service.business.IUserService;
import com.mayankmadhav.demo.app.mobileappws.transformer.GenericTransformer;
import com.mayankmadhav.demo.app.mobileappws.utils.RestUtils;

@Component
public class AddressTransformer implements GenericTransformer<Address, AddressDTO> {
	
	@Autowired
	private IUserService userService;

	@Override
	public Address fromDTO(AddressDTO addr) {
		Users user= userService.findByUsername(RestUtils.getAuthentication().getName());
		return Address.builder().addressLLine(addr.getAddressLLine()).city(addr.getCity()).state(addr.getState())
				.country(addr.getCountry()).pincode(addr.getPincode())
				.addressType(AddressType.valueOf(addr.getAddressType())).phone(addr.getPhone()).user(user).build();
	}

	@Override
	public AddressDTO toDTO(Address entity) {
		return AddressDTO.builder().addressLLine(entity.getAddressLLine()).city(entity.getCity())
				.state(entity.getState()).country(entity.getCountry()).pincode(entity.getPincode())
				.addressType(entity.getAddressType().toString()).phone(entity.getPhone()).build();
	}

	@Override
	public PageDTO<AddressDTO> toPageDto(Page<Address> page) {
		// TODO Auto-generated method stub
		return null;
	}

}

package com.mayankmadhav.demo.app.mobileappws.service.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.mayankmadhav.demo.app.mobileappws.constants.enums.Roles;
import com.mayankmadhav.demo.app.mobileappws.controller.dtos.RoleDTO;
import com.mayankmadhav.demo.app.mobileappws.exceptions.EntityAlreadyExistsException;
import com.mayankmadhav.demo.app.mobileappws.models.Role;
import com.mayankmadhav.demo.app.mobileappws.repository.IRoleRepository;
import com.mayankmadhav.demo.app.mobileappws.service.AbstractService;
import com.mayankmadhav.demo.app.mobileappws.service.business.IRoleService;
import com.mayankmadhav.demo.app.mobileappws.transformer.impl.RoleTransformer;

@Service
public class RoleServiceImpl extends AbstractService<Role, Long> implements IRoleService {

	@Autowired
	private IRoleRepository roleRepository;

	@Autowired
	private RoleTransformer roleTransformer;

	@Override
	protected JpaRepository<Role, Long> repository() {
		return roleRepository;
	}

	@Override
	public Role addRole(RoleDTO roleDTO) {
		if (!Roles.isValid(roleDTO.getName())) {
			throw new EntityAlreadyExistsException("BAD_REQUEST");
		}
		Role role = roleRepository.findByName(Roles.valueOf(roleDTO.getName()));
		role = save(roleTransformer.fromDTO(roleDTO));
		if (role != null) {
			throw new EntityAlreadyExistsException();
		}
		return role;
	}

	@Override
	public Role findByName(String role) {
		return roleRepository.findByName(Roles.valueOf(role));
	}

}

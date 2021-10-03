package com.mayankmadhav.demo.app.mobileappws.service.business;

import com.mayankmadhav.demo.app.mobileappws.controller.dtos.RoleDTO;
import com.mayankmadhav.demo.app.mobileappws.models.Role;
import com.mayankmadhav.demo.app.mobileappws.service.base.IGenericService;

public interface IRoleService extends IGenericService<Role, Long> {
	
	public Role addRole(RoleDTO roleDTO);
	
	public Role findByName(String role);

	
}

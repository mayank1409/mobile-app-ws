package com.mayankmadhav.demo.app.mobileappws.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayankmadhav.demo.app.mobileappws.controller.dtos.RoleDTO;
import com.mayankmadhav.demo.app.mobileappws.service.business.IRoleService;
import com.mayankmadhav.demo.app.mobileappws.transformer.impl.RoleTransformer;
import com.mayankmadhav.demo.app.mobileappws.utils.RestResponse;
import com.mayankmadhav.demo.app.mobileappws.utils.RestUtils;

@RestController
@RequestMapping(value = "/api/v1/roles")
public class RoleController {
	
	@Autowired
	private RoleTransformer roleTransformer;
	
	@Autowired
	private IRoleService roleService;
	
	@PostMapping()
	public ResponseEntity<RestResponse<RoleDTO>> addRole(@Valid @RequestBody RoleDTO roleDto) {
		return RestUtils.handleResponse(roleTransformer.toDTO(roleService.addRole(roleDto)));
	}

}

package com.mayankmadhav.demo.app.mobileappws.controller;

import com.mayankmadhav.demo.app.mobileappws.constants.enums.Roles;
import com.mayankmadhav.demo.app.mobileappws.controller.dtos.RoleDTO;
import com.mayankmadhav.demo.app.mobileappws.exceptions.EntityNotFoundException;
import com.mayankmadhav.demo.app.mobileappws.models.Role;
import com.mayankmadhav.demo.app.mobileappws.service.business.IRoleService;
import com.mayankmadhav.demo.app.mobileappws.transformer.impl.RoleTransformer;
import com.mayankmadhav.demo.app.mobileappws.utils.RestResponse;
import com.mayankmadhav.demo.app.mobileappws.utils.RestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/roles")
public class RoleController {

    @Autowired
    private RoleTransformer roleTransformer;

    @Autowired
    private IRoleService roleService;

    @PostMapping
    public ResponseEntity<RestResponse<RoleDTO>> addRole(@Valid @RequestBody RoleDTO roleDto) {
        return RestUtils.handleResponse(roleTransformer.toDTO(roleService.save(roleTransformer.fromDTO(roleDto))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestResponse<RoleDTO>> updateRole(@RequestBody RoleDTO roleDto, @PathVariable("id") Long id) {
        Role role = roleService.get(id);
        if (role == null) {
            throw new EntityNotFoundException("Role not found with id " + id);
        }
        role.setName(Roles.valueOf(roleDto.getName()));
        return RestUtils.handleResponse(roleTransformer.toDTO(roleService.update(role)));
    }

}

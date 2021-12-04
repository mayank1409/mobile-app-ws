package com.mayankmadhav.demo.app.mobileappws.transformer.impl;

import com.mayankmadhav.demo.app.mobileappws.constants.enums.Roles;
import com.mayankmadhav.demo.app.mobileappws.controller.dtos.RoleDTO;
import com.mayankmadhav.demo.app.mobileappws.exceptions.EntityAlreadyExistsException;
import com.mayankmadhav.demo.app.mobileappws.models.Role;
import com.mayankmadhav.demo.app.mobileappws.service.business.IRoleService;
import com.mayankmadhav.demo.app.mobileappws.transformer.GenericTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleTransformer implements GenericTransformer<Role, RoleDTO> {

    @Autowired
    IRoleService roleService;

    @Override
    public Role fromDTO(RoleDTO dto) {
        Role role = roleService.findByName(dto.getName());
        if (role == null) {
            role = Role.builder().name(Roles.valueOf(dto.getName())).build();
            return role;
        }
        throw new EntityAlreadyExistsException("Role " + dto.getName() + " already exists");
    }

    @Override
    public RoleDTO toDTO(Role entity) {
        return RoleDTO.builder().id(entity.getId()).name(entity.getName().toString()).build();
    }

}

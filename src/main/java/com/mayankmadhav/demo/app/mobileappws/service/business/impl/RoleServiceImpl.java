package com.mayankmadhav.demo.app.mobileappws.service.business.impl;

import com.mayankmadhav.demo.app.mobileappws.constants.enums.Roles;
import com.mayankmadhav.demo.app.mobileappws.controller.dtos.RoleDTO;
import com.mayankmadhav.demo.app.mobileappws.exceptions.EntityAlreadyExistsException;
import com.mayankmadhav.demo.app.mobileappws.exceptions.EntityNotFoundException;
import com.mayankmadhav.demo.app.mobileappws.models.Role;
import com.mayankmadhav.demo.app.mobileappws.repository.IRoleRepository;
import com.mayankmadhav.demo.app.mobileappws.service.AbstractService;
import com.mayankmadhav.demo.app.mobileappws.service.business.IRoleService;
import com.mayankmadhav.demo.app.mobileappws.transformer.impl.RoleTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

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
    public Role findByName(String role) {
        Role roleByName = roleRepository.findByName(Roles.valueOf(role));
        return roleByName;
    }
}

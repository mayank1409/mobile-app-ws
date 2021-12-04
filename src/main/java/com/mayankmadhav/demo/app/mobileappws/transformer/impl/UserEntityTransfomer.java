package com.mayankmadhav.demo.app.mobileappws.transformer.impl;

import com.mayankmadhav.demo.app.mobileappws.constants.enums.Roles;
import com.mayankmadhav.demo.app.mobileappws.controller.dtos.UserDTO;
import com.mayankmadhav.demo.app.mobileappws.models.UserEntity;
import com.mayankmadhav.demo.app.mobileappws.service.business.IRoleService;
import com.mayankmadhav.demo.app.mobileappws.service.business.IUserEntityService;
import com.mayankmadhav.demo.app.mobileappws.transformer.GenericTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserEntityTransfomer implements GenericTransformer<UserEntity, UserDTO> {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bcryptPasswordEncoder;

    @Autowired
    private IUserEntityService userService;

    @Override
    public UserEntity fromDTO(UserDTO dto) {

        UserEntity user = userService.findByUsername(dto.getEmail());

        if (user == null) {
            user = UserEntity.builder().firstName(dto.getFirstName())
                    .lastName(dto.getLastName()).mobile(dto.getMobile())
                    .role(roleService.findByName(Roles.USER.toString())).email(dto.getEmail()).encPassword(bcryptPasswordEncoder.encode(dto.getPassword())).build();
        } else {
            user.setUserProfile(dto.getUserProfile());
            user.getUserProfile().setAddress(dto.getUserProfile().getAddress());
        }
        return user;
    }

    @Override
    public UserDTO toDTO(UserEntity entity) {
        UserDTO dto = UserDTO.builder().firstName(entity.getFirstName()).lastName(entity.getLastName())
                .email(entity.getEmail()).mobile(entity.getMobile()).build();
        if (entity.getUserProfile() != null) {
            dto.setUserProfile(entity.getUserProfile());
            dto.getUserProfile().setAddress(entity.getUserProfile().getAddress());
        }
        return dto;
    }

}

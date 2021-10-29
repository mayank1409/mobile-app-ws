package com.mayankmadhav.demo.app.mobileappws.controller;

import com.mayankmadhav.demo.app.mobileappws.controller.dtos.UserDTO;
import com.mayankmadhav.demo.app.mobileappws.service.business.IUserEntityService;
import com.mayankmadhav.demo.app.mobileappws.transformer.impl.UserEntityTransfomer;
import com.mayankmadhav.demo.app.mobileappws.utils.RestResponse;
import com.mayankmadhav.demo.app.mobileappws.utils.RestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserEntityController {

    @Autowired
    private IUserEntityService userEntityService;

    @Autowired
    private UserEntityTransfomer userEntityTransfomer;

    @PostMapping("/profiles")
    public ResponseEntity<RestResponse<UserDTO>> completeProfile(@Valid @RequestBody UserDTO userDto) {
        return RestUtils
                .handleResponse(userEntityTransfomer.toDTO(userEntityService.save(userEntityTransfomer.fromDTO(userDto))));
    }

    @GetMapping("/profiles")
    public ResponseEntity<RestResponse<UserDTO>> getUserProfile() {
        String username = RestUtils.getAuthentication().getName();
        return RestUtils
                .handleGetResponse(userEntityTransfomer.toDTO(userEntityService.findByUsername(username)));
    }

}

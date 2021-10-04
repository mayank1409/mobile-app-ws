package com.mayankmadhav.demo.app.mobileappws.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayankmadhav.demo.app.mobileappws.controller.dtos.UserProfileDTO;
import com.mayankmadhav.demo.app.mobileappws.service.business.IUserProfileService;
import com.mayankmadhav.demo.app.mobileappws.service.business.IUserService;
import com.mayankmadhav.demo.app.mobileappws.transformer.impl.UserProfileTransformer;
import com.mayankmadhav.demo.app.mobileappws.utils.RestResponse;
import com.mayankmadhav.demo.app.mobileappws.utils.RestUtils;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserController {

	@Autowired
	private IUserProfileService userProfileService;

	@Autowired
	private IUserService userService;

	@Autowired
	private UserProfileTransformer userprofileTransformer;

	@PostMapping("/profiles")
	public ResponseEntity<RestResponse<UserProfileDTO>> completeProfile(@Valid @RequestBody UserProfileDTO userDto) {
		String username = RestUtils.getAuthentication().getName();
		return RestUtils
				.handleResponse(userprofileTransformer.toDTO(userProfileService.completeProfile(userDto, username)));
	}

	@GetMapping("/profiles")
	public ResponseEntity<RestResponse<UserProfileDTO>> getUserProfile() {
		String username = RestUtils.getAuthentication().getName();
		return RestUtils
				.handleGetResponse(userprofileTransformer.toDTO(userService.findByUsername(username).getUserProfile()));
	}

}

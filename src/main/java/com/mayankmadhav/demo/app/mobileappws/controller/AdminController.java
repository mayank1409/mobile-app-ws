package com.mayankmadhav.demo.app.mobileappws.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayankmadhav.demo.app.mobileappws.controller.dtos.CompleteUserProfileDTO;
import com.mayankmadhav.demo.app.mobileappws.utils.RestResponse;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

	@PostMapping("/users/{userId}")
	public ResponseEntity<RestResponse<CompleteUserProfileDTO>> saveUser(
			@RequestBody CompleteUserProfileDTO userProfileDto, @PathVariable String userId) {
		return null;
	}
}

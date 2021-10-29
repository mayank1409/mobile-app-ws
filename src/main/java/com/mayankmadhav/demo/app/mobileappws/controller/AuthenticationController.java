package com.mayankmadhav.demo.app.mobileappws.controller;

import com.mayankmadhav.demo.app.mobileappws.constants.MessageConstants;
import com.mayankmadhav.demo.app.mobileappws.controller.dtos.LoginDTO;
import com.mayankmadhav.demo.app.mobileappws.controller.dtos.LoginResponseDTO;
import com.mayankmadhav.demo.app.mobileappws.controller.dtos.UserDTO;
import com.mayankmadhav.demo.app.mobileappws.exceptions.UserAuthenticationException;
import com.mayankmadhav.demo.app.mobileappws.jwts.utils.JwtUtils;
import com.mayankmadhav.demo.app.mobileappws.service.CustomUserDetailsService;
import com.mayankmadhav.demo.app.mobileappws.service.business.IUserEntityService;
import com.mayankmadhav.demo.app.mobileappws.transformer.impl.UserEntityTransfomer;
import com.mayankmadhav.demo.app.mobileappws.utils.RestResponse;
import com.mayankmadhav.demo.app.mobileappws.utils.RestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private IUserEntityService userService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserEntityTransfomer userTransformer;

    @PostMapping("/signup")
    public ResponseEntity<RestResponse<UserDTO>> saveUser(@Valid @RequestBody UserDTO userDto) {
        return RestUtils.handleResponse(userTransformer.toDTO(userService.save(userTransformer.fromDTO(userDto))),
                MessageConstants.CREATED_MESSAGE);
    }

    @PostMapping("/login")
    public ResponseEntity<RestResponse<LoginResponseDTO>> login(@Valid @RequestBody LoginDTO loginDto) {

        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        } catch (BadCredentialsException ex) {
            throw new UserAuthenticationException(ex.getMessage());
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getEmail());

        String jwtToken = jwtUtils.generateToken(userDetails);

        return RestUtils.handleResponse(new LoginResponseDTO(jwtToken));
    }
}

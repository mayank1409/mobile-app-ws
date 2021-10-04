package com.mayankmadhav.demo.app.mobileappws.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mayankmadhav.demo.app.mobileappws.controller.dtos.UserDTO;
import com.mayankmadhav.demo.app.mobileappws.exceptions.EntityAlreadyExistsException;
import com.mayankmadhav.demo.app.mobileappws.models.Privileges;
import com.mayankmadhav.demo.app.mobileappws.models.Users;
import com.mayankmadhav.demo.app.mobileappws.repository.IUserRepository;
import com.mayankmadhav.demo.app.mobileappws.service.business.IUserService;
import com.mayankmadhav.demo.app.mobileappws.transformer.impl.UserTransfomer;

import lombok.extern.slf4j.Slf4j;

@Transactional
@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private UserTransfomer userTransformer;

	@Autowired
	private IUserService userService;

	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with " + username);
		}

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Privileges privilege : user.getRole().getPrivileges()) {
			authorities.add(new SimpleGrantedAuthority(privilege.getName()));
		}
		return new User(user.getEmail(), user.getEncPassword(), authorities);
	}

	public Users signUp(UserDTO userDto) {

		Users user = userService.findByUsername(userDto.getEmail());
		if (user != null) {
			throw new EntityAlreadyExistsException("You are already registered, Please login!");
		}

		user = userRepository.findByMobile(userDto.getMobile());
		if (user != null) {
			throw new EntityAlreadyExistsException("You are already registered with this mobile number!");
		}
		user = userTransformer.fromDTO(userDto);
		user.setEncPassword(bcryptPasswordEncoder.encode(userDto.getPassword()));
		user = userService.save(user);
		log.info("user saved successfully");
		return user;
	}

}

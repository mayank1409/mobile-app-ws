package com.mayankmadhav.demo.app.mobileappws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.mayankmadhav.demo.app.mobileappws.constants.enums.Roles;
import com.mayankmadhav.demo.app.mobileappws.controller.dtos.UserDTO;
import com.mayankmadhav.demo.app.mobileappws.exceptions.EntityAlreadyExistsException;
import com.mayankmadhav.demo.app.mobileappws.exceptions.EntityNotFoundException;
import com.mayankmadhav.demo.app.mobileappws.models.Privileges;
import com.mayankmadhav.demo.app.mobileappws.models.Role;
import com.mayankmadhav.demo.app.mobileappws.models.Users;
import com.mayankmadhav.demo.app.mobileappws.repository.IPrivilegesRepository;
import com.mayankmadhav.demo.app.mobileappws.service.CustomUserDetailsService;
import com.mayankmadhav.demo.app.mobileappws.service.business.IRoleService;
import com.mayankmadhav.demo.app.mobileappws.service.business.IUserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private IPrivilegesRepository privilegesRepository;

	@Autowired
	private IUserService userService;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		log.info("application startup event listener");

		List<Role> roles = roleService.list();

		log.info("svaing roles and privileges");
		if (roles == null || roles.isEmpty()) {

			Role userRole = Role.builder().name(Roles.DEFAULT_USER).build();
			Role adminRole = Role.builder().name(Roles.ADMIN).build();

			List<Privileges> privileges = List.of(Privileges.builder().name("WRITE_PRIVILEGES").role(userRole).build(),
					Privileges.builder().name("READ_PRIVILEGE").role(userRole).build(),
					Privileges.builder().name("DELETE_PRIVILEGE").role(adminRole).build(),
					Privileges.builder().name("UPDATE_PRIVILEGE").role(adminRole).build());
			privilegesRepository.saveAll(privileges);

		}
		log.info("roles and privileges are already added");

		log.info("creating admin user with default credentials");

		UserDTO userDto = UserDTO.builder().firstName("admin").lastName("appuser").email("admin@appuser.in")
				.password("appuser").mobile("10123").build();
		try {
			Users admin = userDetailsService.signUp(userDto);
			admin.setRole(roleService.findByName(Roles.ADMIN.toString()));

			userService.save(admin);
		} catch (EntityAlreadyExistsException ex) {
			log.warn(ex.getMessage());
		} catch (EntityNotFoundException ex) {
			log.warn("role not found {}", ex.getMessage());
		} catch (Exception e) {
			log.error("something unexpected happened {}", e.getMessage());
		}

	}

}

package com.mayankmadhav.demo.app.mobileappws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.mayankmadhav.demo.app.mobileappws.constants.enums.Roles;
import com.mayankmadhav.demo.app.mobileappws.models.Privileges;
import com.mayankmadhav.demo.app.mobileappws.models.Role;
import com.mayankmadhav.demo.app.mobileappws.models.Users;
import com.mayankmadhav.demo.app.mobileappws.repository.IPrivilegesRepository;
import com.mayankmadhav.demo.app.mobileappws.repository.IUserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IPrivilegesRepository privilegesRepository;

	@Autowired
	private PasswordEncoder bcryptPasswordEncoder;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		log.info("application startup event listener");
		log.info("creating admin user with default credentials");

		String email = "admin@appuser.in";
		Users admin = userRepository.findByEmail(email);

		if (admin == null) {

			log.info("svaing roles and privileges");
			
			Role userRole = Role.builder().name(Roles.DEFAULT_USER).build();
			Role adminRole = Role.builder().name(Roles.ADMIN).build();
			
			List<Privileges> privileges = List.of(Privileges.builder().name("WRITE_PRIVILEGES").role(userRole).build(),
					Privileges.builder().name("READ_PRIVILEGE").role(userRole).build(),
					Privileges.builder().name("DELETE_PRIVILEGE").role(adminRole).build(),
					Privileges.builder().name("UPDATE_PRIVILEGE").role(adminRole).build());
			privilegesRepository.saveAll(privileges);

			admin = Users.builder().firstName("admin").lastName("appuser").email(email)
					.encPassword(bcryptPasswordEncoder.encode("appuser")).build();

			admin.setRole(adminRole);

			userRepository.save(admin);

		} else {
			log.info("admin already exists");
		}
	}

}

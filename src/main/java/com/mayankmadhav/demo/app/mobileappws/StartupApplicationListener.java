package com.mayankmadhav.demo.app.mobileappws;

import com.mayankmadhav.demo.app.mobileappws.constants.enums.Roles;
import com.mayankmadhav.demo.app.mobileappws.controller.dtos.UserDTO;
import com.mayankmadhav.demo.app.mobileappws.exceptions.EntityAlreadyExistsException;
import com.mayankmadhav.demo.app.mobileappws.exceptions.EntityNotFoundException;
import com.mayankmadhav.demo.app.mobileappws.models.Privileges;
import com.mayankmadhav.demo.app.mobileappws.models.Role;
import com.mayankmadhav.demo.app.mobileappws.models.UserEntity;
import com.mayankmadhav.demo.app.mobileappws.repository.IPrivilegesRepository;
import com.mayankmadhav.demo.app.mobileappws.repository.IRoleRepository;
import com.mayankmadhav.demo.app.mobileappws.repository.IUserEntityRepository;
import com.mayankmadhav.demo.app.mobileappws.service.business.IRoleService;
import com.mayankmadhav.demo.app.mobileappws.service.business.IUserEntityService;
import com.mayankmadhav.demo.app.mobileappws.transformer.impl.UserEntityTransfomer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class StartupApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private IPrivilegesRepository privilegesRepository;

    @Autowired
    private IUserEntityRepository userEntityRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private UserEntityTransfomer userEntityTransfomer;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("application startup event listener");

        List<Role> roles = roleRepository.findAll();

        if (roles == null || roles.isEmpty()) {
            log.info("saving roles and privileges");

            Role userRole = Role.builder().name(Roles.USER).build();
            Role adminRole = Role.builder().name(Roles.ADMIN).build();

            List<Privileges> privileges = List.of(Privileges.builder().name("WRITE_PRIVILEGES").role(userRole).build(),
                    Privileges.builder().name("READ_PRIVILEGE").role(userRole).build(),
                    Privileges.builder().name("DELETE_PRIVILEGE").role(adminRole).build(),
                    Privileges.builder().name("UPDATE_PRIVILEGE").role(adminRole).build());
            privilegesRepository.saveAll(privileges);

            log.info("creating admin user with default credentials");
            UserEntity entity = UserEntity.builder().firstName("admin").lastName("appuser").email("admin@appuser.in")
                    .encPassword(bCryptPasswordEncoder.encode("appuser")).mobile("10123").role(adminRole).build();
            try {
                UserEntity admin = userEntityRepository.save(entity);
            } catch (EntityAlreadyExistsException ex) {
                log.warn(ex.getMessage());
            } catch (EntityNotFoundException ex) {
                log.warn("role not found {}", ex.getMessage());
            } catch (Exception e) {
                log.error("something unexpected happened {}", e.getMessage());
            }
        }
        log.info("roles and privileges are already saved");
    }
}

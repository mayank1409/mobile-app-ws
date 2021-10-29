package com.mayankmadhav.demo.app.mobileappws.repository;

import com.mayankmadhav.demo.app.mobileappws.constants.enums.Roles;
import com.mayankmadhav.demo.app.mobileappws.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

    public Role findByName(Roles name);
}

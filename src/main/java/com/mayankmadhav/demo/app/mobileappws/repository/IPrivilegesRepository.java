package com.mayankmadhav.demo.app.mobileappws.repository;

import com.mayankmadhav.demo.app.mobileappws.models.Privileges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPrivilegesRepository extends JpaRepository<Privileges, Long> {

}

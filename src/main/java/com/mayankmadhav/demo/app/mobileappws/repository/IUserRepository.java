package com.mayankmadhav.demo.app.mobileappws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mayankmadhav.demo.app.mobileappws.models.Users;

@Repository
public interface IUserRepository extends JpaRepository<Users, Long> {

	public Users findByEmail(String email);

	public Users findByMobile(String mobile);

	public Users findByUserId(String userId);

}

package com.mayankmadhav.demo.app.mobileappws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mayankmadhav.demo.app.mobileappws.models.UserProfile;

@Repository
public interface IUserProfileRepository extends JpaRepository<UserProfile, Long> {

}

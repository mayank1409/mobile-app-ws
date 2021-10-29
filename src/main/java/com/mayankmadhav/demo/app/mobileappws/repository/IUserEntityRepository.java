package com.mayankmadhav.demo.app.mobileappws.repository;

import com.mayankmadhav.demo.app.mobileappws.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserEntityRepository extends JpaRepository<UserEntity, Long> {

    public UserEntity findByEmail(String email);

    public UserEntity findByMobile(String mobile);

}

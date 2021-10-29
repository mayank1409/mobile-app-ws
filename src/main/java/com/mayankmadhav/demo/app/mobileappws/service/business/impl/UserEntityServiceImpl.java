package com.mayankmadhav.demo.app.mobileappws.service.business.impl;

import com.mayankmadhav.demo.app.mobileappws.models.UserEntity;
import com.mayankmadhav.demo.app.mobileappws.repository.IUserEntityRepository;
import com.mayankmadhav.demo.app.mobileappws.service.AbstractService;
import com.mayankmadhav.demo.app.mobileappws.service.business.IUserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserEntityServiceImpl extends AbstractService<UserEntity, Long> implements IUserEntityService {

    private IUserEntityRepository userRepository;

    @Autowired
    public UserEntityServiceImpl(IUserEntityRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected JpaRepository<UserEntity, Long> repository() {
        return userRepository;
    }

    @Override
    public UserEntity findByUsername(String username) {
        UserEntity byEmail = userRepository.findByEmail(username);
        return byEmail == null ? null : byEmail;
    }

}

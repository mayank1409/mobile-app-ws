package com.mayankmadhav.demo.app.mobileappws.service.business;

import com.mayankmadhav.demo.app.mobileappws.models.UserEntity;
import com.mayankmadhav.demo.app.mobileappws.service.base.IGenericService;

public interface IUserEntityService extends IGenericService<UserEntity, Long> {

    public UserEntity findByUsername(String username);

}

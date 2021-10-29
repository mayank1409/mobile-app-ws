package com.mayankmadhav.demo.app.mobileappws.service;

import com.mayankmadhav.demo.app.mobileappws.models.Privileges;
import com.mayankmadhav.demo.app.mobileappws.models.UserEntity;
import com.mayankmadhav.demo.app.mobileappws.repository.IUserEntityRepository;
import com.mayankmadhav.demo.app.mobileappws.service.business.IUserEntityService;
import com.mayankmadhav.demo.app.mobileappws.transformer.impl.UserEntityTransfomer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserEntityRepository userRepository;

    @Autowired
    private UserEntityTransfomer userTransformer;

    @Autowired
    private IUserEntityService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with " + username);
        }

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (Privileges privilege : user.getRole().getPrivileges()) {
            authorities.add(new SimpleGrantedAuthority(privilege.getName()));
        }
        return new User(user.getEmail(), user.getEncPassword(), authorities);
    }
}

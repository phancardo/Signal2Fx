package com.projetCloud.projetCloudRESTWS.service;

import com.projetCloud.projetCloudRESTWS.model.CustomUserDetails;
import com.projetCloud.projetCloudRESTWS.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(username);
        if(user == null)
            throw new UsernameNotFoundException("User not found!");
        return new CustomUserDetails(user);
    }
}

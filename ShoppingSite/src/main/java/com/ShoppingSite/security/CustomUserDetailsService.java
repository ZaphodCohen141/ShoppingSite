package com.ShoppingSite.security;

import com.ShoppingSite.model.user.CustomUser;
import com.ShoppingSite.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUser customUser = userService.findUserByUsername(username);

        if(customUser == null){
            throw new UsernameNotFoundException("The provided username can't be found");
        }
        return User.withDefaultPasswordEncoder().username(customUser.getUsername()).password(customUser.getPassword()).roles().build();
    }
}

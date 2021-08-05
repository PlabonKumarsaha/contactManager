package com.pks.contact_manager.configuration;

import com.pks.contact_manager.dao.UserRepository;
import com.pks.contact_manager.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    //the user's user Name is loaded in the userName variable
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //fetch user from database

        User user= userRepository.getUserByUserName(userName);

        if(user == null) {
            throw new UsernameNotFoundException("could not find exception");
        }
        CustomUserDetails customUserDetails = new CustomUserDetails(user);
        return customUserDetails;
    }
}

package com.apiVerse.UserCase.service.impl;

import lombok.AllArgsConstructor;
import com.apiVerse.UserCase.entity.User;
import com.apiVerse.UserCase.repository.UserRepo;
import com.apiVerse.UserCase.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findUserByUsername(username).orElseThrow(()
                -> new UsernameNotFoundException(String.format("User '%s' not found", username)
        ));

        System.out.println(user.getPassword());
        return UserDetailsImpl.build(user);
    }

}

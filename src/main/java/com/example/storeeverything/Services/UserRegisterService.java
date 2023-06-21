package com.example.storeeverything.Services;

import com.example.storeeverything.Dtos.UserDto;
import com.example.storeeverything.Dtos.UserRegistrationDto;
import com.example.storeeverything.Entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public interface UserRegisterService extends UserDetailsService {

    User save(UserRegistrationDto registrationDto);
    UserDetails loadUserByUsername(String username);

}

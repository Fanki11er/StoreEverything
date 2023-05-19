package com.example.storeeverything.Services;

import com.example.storeeverything.Dtos.UserRegistrationDto;
import com.example.storeeverything.Entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationDto registrationDto);
}

package com.example.storeeverything.Services;

import com.example.storeeverything.Dtos.UserRegistrationDto;
import com.example.storeeverything.Entities.User;

public interface UserService {

    User sve(UserRegistrationDto registrationDto);
}

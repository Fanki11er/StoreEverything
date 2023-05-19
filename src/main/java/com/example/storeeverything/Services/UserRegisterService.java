package com.example.storeeverything.Services;

import com.example.storeeverything.Dtos.UserRegistrationDto;
import com.example.storeeverything.Entities.User;
import com.example.storeeverything.Repositories.UserRepository;
import com.example.storeeverything.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterService {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getFirstName(),
                registrationDto.getSurname(),
                registrationDto.getLogin(),
                passwordEncoder.encode(registrationDto.getPassword()),
                registrationDto.getAge(),
                Role.LIMITED);
        return  userRepository.save(user);
    }
}

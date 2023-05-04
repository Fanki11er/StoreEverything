package com.example.storeeverything.Services;

import com.example.storeeverything.Dtos.UserRegistrationDto;
import com.example.storeeverything.Entities.User;
import com.example.storeeverything.Repositories.UserRepository;
import com.example.storeeverything.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public User sve(UserRegistrationDto registrationDto) {
        User user = new User(registrationDto.getFirstName(),
                            registrationDto.getSurname(),
                            registrationDto.getLogin(),
                            registrationDto.getPassword(),
                            registrationDto.getAge(),
                            Role.LIMITED);
        return  userRepository.save(user);
    }
}

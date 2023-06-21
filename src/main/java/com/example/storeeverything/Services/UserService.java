package com.example.storeeverything.Services;

import com.example.storeeverything.Dtos.UserDto;
import com.example.storeeverything.Entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public interface UserService {

    UserDto loadUserById(Long id) throws UsernameNotFoundException;

    String getLoggedUserRole();

    User getLoggedUserEntity();

    Long getLoggedUserId();

    List<UserDto> loadUsers() throws UsernameNotFoundException;

    User getUserEntityById(Long id);

    ArrayList<UserDto> getUsers();

    void changeUserRole(Long id, String newRole);
}

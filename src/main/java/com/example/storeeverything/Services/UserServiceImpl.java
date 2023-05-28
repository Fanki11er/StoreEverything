package com.example.storeeverything.Services;
import com.example.storeeverything.Dtos.SecurityUserDto;
import com.example.storeeverything.Dtos.UserDto;
import com.example.storeeverything.Entities.User;
import com.example.storeeverything.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username).map(SecurityUserDto::new).orElseThrow(() -> new UsernameNotFoundException("Nie prawidłowe dane"));
    }

    public UserDto loadUserById(Long id) throws  UsernameNotFoundException{
        return userRepository.findById (id).map(UserDto::new).orElseThrow(()-> new UsernameNotFoundException("Nie znaleziono użytkonika"));
    }

    public String getLoggedUserRole(){
        SecurityUserDto auth = (SecurityUserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return  auth.getRole();
    }

    public User getLoggedUserEntity(){
        SecurityUserDto auth = (SecurityUserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findByLogin(auth.getUsername()).orElseThrow(() -> new UsernameNotFoundException("Nie znaleziono użytkownika"));
        return user;
    }

    public Long getLoggedUserId(){
        User user = getLoggedUserEntity();
        return user.getId();
    }

    public List<UserDto> loadUsers() throws  UsernameNotFoundException{
        Long loggedUserId = getLoggedUserId();
        List<User> allUsers = userRepository.findAll();

        List<UserDto> userDtos = allUsers.stream().filter(u -> u.getId() != loggedUserId ).
                map(UserDto::new)
                .collect(Collectors.toList());
        return userDtos;
    }

    public User getUserEntityById(Long id){
        User user = userRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Nie znaleziono uzytkownika"));
        return user;
    }
}


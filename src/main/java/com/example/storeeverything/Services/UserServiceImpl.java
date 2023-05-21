package com.example.storeeverything.Services;
import com.example.storeeverything.Dtos.SecurityUserDto;
import com.example.storeeverything.Dtos.UserDto;
import com.example.storeeverything.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByLogin(username).map(SecurityUserDto::new).orElseThrow(() -> new UsernameNotFoundException("Nie prawidłowe dane"));
    }

    public UserDto loadUserDtoByUsername(String username) throws  UsernameNotFoundException{
        return userRepository.findByLogin(username).map(UserDto::new).orElseThrow(()-> new UsernameNotFoundException("Nie znaleziono użytkonika"));
    }
}

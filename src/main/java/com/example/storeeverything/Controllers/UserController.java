package com.example.storeeverything.Controllers;
import com.example.storeeverything.Dtos.SecurityUserDto;
import com.example.storeeverything.Dtos.UserDto;
import com.example.storeeverything.Entities.User;
import com.example.storeeverything.Services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/App/User")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/Me")
    public  String getUserInfo( Model model){
        SecurityUserDto auth = (SecurityUserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDto user = userService.loadUserDtoByUsername(auth.getUsername());

        model.addAttribute("me", user);
        model.addAttribute("path", "User");
        return "appUser";
    }
}

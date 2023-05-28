package com.example.storeeverything.Controllers;
import com.example.storeeverything.Dtos.UserDto;
import com.example.storeeverything.Services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/App/Admin")
public class AdminController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/User")
    public  String getUserInfo(@RequestParam Long id, Model model){
        UserDto user = userService.loadUserById(id);
        String loggedUserRole = userService.getLoggedUserRole();
        model.addAttribute("user", user);
        model.addAttribute("path", "User");
        model.addAttribute("loggedUserRole", loggedUserRole);
        return "editUser";
    }
}

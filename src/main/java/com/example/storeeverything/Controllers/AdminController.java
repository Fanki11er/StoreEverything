package com.example.storeeverything.Controllers;
import com.example.storeeverything.Dtos.UserDto;
import com.example.storeeverything.Dtos.NewRoleDto;
import com.example.storeeverything.Role;
import com.example.storeeverything.Services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/App/Admin")
public class AdminController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/User")
    public  String getUserInfo(@RequestParam Long id, Model model){
        UserDto user = userService.loadLUserById(id);
        String loggedUserRole = userService.getLoggedUserRole();
        ArrayList<String> availableRoles = new ArrayList<>();

        for (int i=0; i < Role.values().length; i++) {
            if(!Role.values()[i].name().equals(loggedUserRole)){
                availableRoles.add(Role.values()[i].name());
            }
        }

        model.addAttribute("user", user);
        model.addAttribute("path", "User");
        model.addAttribute("loggedUserRole", loggedUserRole);
        model.addAttribute("roles", availableRoles);
        model.addAttribute("newRole", new NewRoleDto());
        return "editUser";
    }

    @PostMapping("/User/Edit")
    public String editUserRole(@RequestParam Long id, @ModelAttribute("newRole") NewRoleDto newRole){
        System.out.println(newRole.getNewRole());
        return "redirect:/App/Admin/User?id=" + id;
    }

}

package com.example.storeeverything.Controllers;
import com.example.storeeverything.Dtos.UserDto;
import com.example.storeeverything.Dtos.NewRoleDto;
import com.example.storeeverything.Role;
import com.example.storeeverything.Services.UserServiceImpl;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/App/Admin")
public class AdminController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/")
    public  String getUsers(Model model){
        String loggedUserRole = userService.getLoggedUserRole();
        ArrayList<UserDto> users = userService.getUsers();

        model.addAttribute("path", "Admin");
        model.addAttribute("loggedUserRole", loggedUserRole);
        model.addAttribute("users", users);

        return "users";
    }
    @GetMapping("/User/{id}")
    public  String getUserInfo(@PathVariable("id") Long id, Model model){
        UserDto user = userService.loadLUserById(id);
        String loggedUserRole = userService.getLoggedUserRole();
        ArrayList<String> availableRoles = new ArrayList<>();

        for (int i=0; i < Role.values().length; i++) {
            if(!Role.values()[i].name().equals(user.getRole())){
                availableRoles.add(Role.values()[i].name());
            }
        }

        model.addAttribute("user", user);
        model.addAttribute("path", "Admin");
        model.addAttribute("loggedUserRole", loggedUserRole);
        model.addAttribute("roles", availableRoles);
        model.addAttribute("newRole", new NewRoleDto());
        return "editUser";
    }

    @PostMapping("/User/Edit/{id}")
    public String editUserRole(@PathVariable Long id, @ModelAttribute("newRole") NewRoleDto newRole){
        userService.changeUserRole(id, newRole.getNewRole());
        return "redirect:/App/Admin/User/{id}?success";
    }

}

package com.example.storeeverything.Controllers;

import com.example.storeeverything.Dtos.UserRegistrationDto;
import com.example.storeeverything.Services.UserRegisterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Auth")
public class AuthController {

    @Autowired
    private UserRegisterService userService;
    @GetMapping("/Register")
    public  String showRegistrationForm( Model model){
        model.addAttribute("user",new UserRegistrationDto() );
        model.addAttribute("path", "Register");
        return "registration";
    }
    @PostMapping("/Register/Reg")
    public String registerUserAccount(@Valid @ModelAttribute("user") UserRegistrationDto userRegistrationDto, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("path", "Register");
        return  "registration";
    }
    userService.save(userRegistrationDto);
    return "redirect:/Auth/Login?success";
    }

    @GetMapping("/Login")
    public  String login(Model model){
        model.addAttribute("path", "Login");
        return "login";
    }
}

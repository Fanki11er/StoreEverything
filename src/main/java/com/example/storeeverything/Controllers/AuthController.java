package com.example.storeeverything.Controllers;

import com.example.storeeverything.Dtos.UserRegistrationDto;
import com.example.storeeverything.Services.UserService;
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
    private UserService userService;
    @GetMapping
    public  String showRegistrationForm(Model model){
        model.addAttribute("user",new UserRegistrationDto() );
        return "registration";
    }
    @PostMapping("/Register")
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto userRegistrationDto, BindingResult result){
    if(result.hasErrors()){
        return  "registration";
    }

    userService.sve(userRegistrationDto);
    return "redirect:/Auth/Register?success";
    }
}

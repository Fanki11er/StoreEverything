package com.example.storeeverything.Controllers;

import com.example.storeeverything.Dtos.UserRegistrationDto;
import com.example.storeeverything.Services.UserRegisterService;
import com.example.storeeverything.Services.UserService;
import com.example.storeeverything.Services.UserServiceImpl;
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
    public  String showRegistrationForm(Model model){
        model.addAttribute("user",new UserRegistrationDto() );
        return "registration";
    }
    @PostMapping("/Register/Reg")
    public String registerUserAccount(@Valid @ModelAttribute("user") UserRegistrationDto userRegistrationDto, BindingResult result){
        if(result.hasErrors()){
        return  "registration";
    }
    userService.save(userRegistrationDto);
    return "redirect:/Auth/Register?success";
    }

    @GetMapping("/Login")
    public  String login(){
        return "login";
    }
}

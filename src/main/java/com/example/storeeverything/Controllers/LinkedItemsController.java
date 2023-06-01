package com.example.storeeverything.Controllers;

import com.example.storeeverything.Dtos.LinkedItemDto;
import com.example.storeeverything.Dtos.ShareItemDto;
import com.example.storeeverything.Entities.Item;
import com.example.storeeverything.Services.LinkedItemsService;
import com.example.storeeverything.Services.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/SharedLinks")
public class LinkedItemsController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private LinkedItemsService linkedItemsService;

    @GetMapping("/{id}")
    public String itemDetails(@PathVariable("id") Long id, Model model) {

        Item item = linkedItemsService.getLinkedItem(id);
        if(item == null){
            return "404";
        }
        model.addAttribute("item", item);
        model.addAttribute("path", "");

        return "sharedLink";
    }
}

package com.example.storeeverything.Controllers;

import com.example.storeeverything.Dtos.LinkedItemDto;
import com.example.storeeverything.Dtos.ShareItemDto;
import com.example.storeeverything.Entities.Item;
import com.example.storeeverything.Entities.SharedItem;
import com.example.storeeverything.Services.LinkedItemsService;
import com.example.storeeverything.Services.SharedItemsService;
import com.example.storeeverything.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/App/Shared")
public class SharedItemsController {

    @Autowired
    SharedItemsService sharedItemsService;
    @Autowired
    UserService userService;
    @Autowired
    private LinkedItemsService linkedItemsService;


    @GetMapping("/Items/IN")
    public String getSharedForUserItems(Model model) {
        String loggedUserRole = userService.getLoggedUserRole();
        List<SharedItem> items = sharedItemsService.getAllLoggedUserSharedItems();
        model.addAttribute("items", items);
        model.addAttribute("path", "Shared");
        model.addAttribute("loggedUserRole", loggedUserRole);
        return "sharedItems";
    }

    @PostMapping("/Items/IN/Share")
    public String shareItem(@Valid @ModelAttribute("sharedItem") ShareItemDto sharedItem, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()) {
            String loggedUserRole = userService.getLoggedUserRole();
            model.addAttribute("path", "Information");
            model.addAttribute("loggedUserRole", loggedUserRole);
            String redirect = String.format("redirect:/App/Items/Share/%d?error", sharedItem.getItemId());
            return redirect;
        }
        sharedItemsService.addSharedItem(sharedItem);
        return String.format("redirect:/App/Items/%d?success", sharedItem.getItemId());
    }

    @GetMapping("/Items/IN/{id}")
    public String itemDetails(@PathVariable("id") Long id, Model model) {
        String loggedUserRole = userService.getLoggedUserRole();
        Item item = sharedItemsService.getSourceItemById(id);
        model.addAttribute("item", item);
        model.addAttribute("path", "Shared");
        model.addAttribute("loggedUserRole", loggedUserRole);
        return "sharedInItemDetails";
    }

    @PostMapping("/Items/OUT/Share")
    public String shareItem(@ModelAttribute("sharedItem") LinkedItemDto linkedItem, Model model){

        String loggedUserRole = userService.getLoggedUserRole();
        model.addAttribute("path", "Information");
        model.addAttribute("loggedUserRole", loggedUserRole);
        linkedItemsService.addLinkedItem(linkedItem);

        return "redirect:/App/Items/Share/" + linkedItem.getItemId();


    }
}

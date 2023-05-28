package com.example.storeeverything.Controllers;
import com.example.storeeverything.Dtos.ItemDto;
import com.example.storeeverything.Entities.Item;
import com.example.storeeverything.Services.CategoryService;
import com.example.storeeverything.Services.ItemService;
import com.example.storeeverything.Services.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/App/Items")
public class ItemController {
    @Autowired
    ItemService itemService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserServiceImpl userService;

    @GetMapping("")
    public String Items(Model model) {
        String loggedUserRole = userService.getLoggedUserRole();
        List<Item> items = itemService.getAllLoggedUserItems();
        model.addAttribute("items", items);
        model.addAttribute("path", "Information");
        model.addAttribute("loggedUserRole", loggedUserRole);
        return "Items";
    }

    @GetMapping("/New")
    public String createItem(Model model) {
        String loggedUserRole = userService.getLoggedUserRole();
        model.addAttribute("item", new ItemDto());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("path", "Information");
        model.addAttribute("loggedUserRole", loggedUserRole);
        return "AddItemForm";
    }

    @PostMapping("/New/Save")
    public String saveItem(@Valid @ModelAttribute("item") ItemDto itemDto, BindingResult bindingResult, Model model) {
        String loggedUserRole = userService.getLoggedUserRole();
        model.addAttribute("path", "Information");
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("loggedUserRole", loggedUserRole);
        if (bindingResult.hasErrors()) {
            return "AddItemForm";
        }
        itemService.addNewItem(itemDto);
        return "redirect:/App/Items";
    }

    @PostMapping("/Delete/{id}")
    public String deleteItem(@PathVariable("id") Long id, Model model) {
        model.addAttribute("path", "Information");
        itemService.deleteItem(id);
        return "redirect:/App/Items";
    }

    @GetMapping("/{id}")
    public String itemDetails(@PathVariable("id") Long id, Model model) {
        String loggedUserRole = userService.getLoggedUserRole();
        Item item = itemService.getItemById(id);
        model.addAttribute("item", item);
        model.addAttribute("path", "Information");
        model.addAttribute("loggedUserRole", loggedUserRole);
        return "itemDetails";
    }

    @GetMapping("/Edit/{id}")
    public String editItemForm(@PathVariable("id") Long id, Model model) {
        String loggedUserRole = userService.getLoggedUserRole();
        Item item = itemService.getItemById(id);
        model.addAttribute("item", item);
        model.addAttribute("newItem", item);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("path", "Information");
        model.addAttribute("loggedUserRole", loggedUserRole);
        return "editItemForm";
    }



    @PostMapping("/Edit/doEdit/{id}")
    public String editItem(@PathVariable("id") Long id, @Valid @ModelAttribute("newItem") ItemDto newItem, BindingResult bindingResult, Model model) {
        Item item = itemService.getItemById(id);
        String loggedUserRole = userService.getLoggedUserRole();
        model.addAttribute("path", "Information");
        model.addAttribute("item", item);
        model.addAttribute("newItem", item);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("loggedUserRole", loggedUserRole);
        if (bindingResult.hasErrors()) {
            return "redirect:/App/Items/Edit/{id}?error";
        }
        itemService.updateItem(id, newItem);
        return "redirect:/App/Items/" + id;
    }
}

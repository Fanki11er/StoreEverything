package com.example.storeeverything.Controllers;

import com.example.storeeverything.Entities.Category;
import com.example.storeeverything.Entities.Item;
import com.example.storeeverything.Entities.User;
import com.example.storeeverything.Services.CategoryService;
import com.example.storeeverything.Services.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Items")
public class ItemController {
    @Autowired
    ItemService itemService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public String Items(Model model) {
        List<Item> items = itemService.getAllItems();
        model.addAttribute("items", items);
        return "Items";
    }

    @GetMapping("/New")
    public String createItem(Model model) {
        model.addAttribute("item", new Item());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "AddItemForm";
    }

    @PostMapping("/Save")
    public String saveItem(@Valid @ModelAttribute("item") Item item, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "AddItemForm";
        }
        itemService.addNewItem(item);
        return "redirect:/Items";
    }

    @PostMapping("/Delete/{id}")
    public String deleteItem(@PathVariable("id") Long id) {
        itemService.deleteItem(id);
        return "redirect:/Items";
    }
}

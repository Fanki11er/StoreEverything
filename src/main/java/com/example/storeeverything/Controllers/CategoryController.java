package com.example.storeeverything.Controllers;

import com.example.storeeverything.Entities.Category;
import com.example.storeeverything.Services.CategoryService;
import com.example.storeeverything.Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("App/Categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    UserService userService;

    @GetMapping("")
    public String Categories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        String loggedUserRole = userService.getLoggedUserRole();
        model.addAttribute("categories", categories);
        model.addAttribute("path", "Categories");
        model.addAttribute("loggedUserRole", loggedUserRole);
        return "Categories";
    }

    @GetMapping("/New")
    public String createCategory(Model model) {
        String loggedUserRole = userService.getLoggedUserRole();
        model.addAttribute("category", new Category());
        model.addAttribute("path", "Categories");
        model.addAttribute("loggedUserRole", loggedUserRole);
        return "AddCategoryForm";
    }

    @PostMapping("/New/Save")
    public String saveCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult, Model model) {
        String loggedUserRole = userService.getLoggedUserRole();
        model.addAttribute("path", "Categories");
        model.addAttribute("loggedUserRole", loggedUserRole);
        if (bindingResult.hasErrors()) {
            return "AddCategoryForm";
        }
        categoryService.addNewCategory(category);
        return "redirect:/App/Categories";
    }
}

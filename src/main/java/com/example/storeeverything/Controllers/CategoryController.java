package com.example.storeeverything.Controllers;

import com.example.storeeverything.Entities.Category;
import com.example.storeeverything.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/Categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/New")
    public String createCategory(Model model) {
        model.addAttribute("category", new Category());
        return "AddCategoryForm";
    }

    @PostMapping("/Save")
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryService.addNewCategory(category);
        return "redirect:/";
    }
}

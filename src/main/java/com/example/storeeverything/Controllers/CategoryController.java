package com.example.storeeverything.Controllers;

import com.example.storeeverything.Entities.Category;
import com.example.storeeverything.Services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("")
    public String Categories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "Categories";
    }

    @GetMapping("/New")
    public String createCategory(Model model) {
        model.addAttribute("category", new Category());
        return "AddCategoryForm";
    }

    @PostMapping("/Save")
    public String saveCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "AddCategoryForm";
        }
        categoryService.addNewCategory(category);
        return "redirect:/Categories";
    }

    @PostMapping("/Delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/Categories";
    }
}

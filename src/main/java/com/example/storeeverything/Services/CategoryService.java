package com.example.storeeverything.Services;

import com.example.storeeverything.Entities.Category;
import com.example.storeeverything.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        return  categoryRepository.findAll();
    }

    public void addNewCategory(Category category){
        categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {categoryRepository.deleteById(id);}
}

package com.example.storeeverything;

import com.example.storeeverything.Entities.Category;
import com.example.storeeverything.Repositories.CategoryRepository;
import com.example.storeeverything.Services.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;


    @Test
    void getAllCategories_ShouldReturnListOfCategories() {
        // Arrange
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());
        categories.add(new Category());

        when(categoryRepository.findAll()).thenReturn(categories);

        // Act
        List<Category> result = categoryService.getAllCategories();

        // Assert
        assertEquals(2, result.size());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void addNewCategory_ShouldSaveCategory() {
        // Arrange
        Category category = new Category();

        // Act
        categoryService.addNewCategory(category);

        // Assert
        verify(categoryRepository, times(1)).save(category);
    }
}

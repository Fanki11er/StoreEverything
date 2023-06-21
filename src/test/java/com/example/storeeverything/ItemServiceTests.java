package com.example.storeeverything;

import com.example.storeeverything.Dtos.ItemDto;
import com.example.storeeverything.Entities.Category;
import com.example.storeeverything.Entities.Item;
import com.example.storeeverything.Repositories.ItemRepository;
import com.example.storeeverything.Services.ItemService;
import com.example.storeeverything.Services.UserService;
import com.example.storeeverything.Services.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private ItemService itemService;

    @Test
    void getAllLoggedUserItems_ShouldReturnListOfItems() {
        // Arrange
        Long userId = 1L;
        List<Item> items = new ArrayList<>();
        items.add(new Item());
        items.add(new Item());

        when(userService.getLoggedUserEntity().getId()).thenReturn(userId);
        when(itemRepository.findAllByUserId(userId)).thenReturn(items);

        // Act
        List<Item> result = itemService.getAllLoggedUserItems();

        // Assert
        assertEquals(2, result.size());
        verify(itemRepository, times(1)).findAllByUserId(userId);
    }

    @Test
    void addNewItem_ShouldSaveItem() {
        // Arrange
        Category category = new Category();
        category.setName("Test");
        ItemDto itemDto = new ItemDto();
        itemDto.setTitle("Test Item");
        itemDto.setContent("Test Content");
        itemDto.setCategory(category);
        itemDto.setUrl("http://example.com");

        // Act
        itemService.addNewItem(itemDto);

        // Assert
        verify(itemRepository, times(1)).save(any(Item.class));
    }

    @Test
    void deleteItem_ShouldDeleteItemById() {
        // Arrange
        Long itemId = 1L;

        // Act
        itemService.deleteItem(itemId);

        // Assert
        verify(itemRepository, times(1)).deleteById(itemId);
    }

    @Test
    void getItemById_WithValidId_ShouldReturnItem() {
        // Arrange
        Long itemId = 1L;
        Item item = new Item();

        when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));

        // Act
        Item result = itemService.getItemById(itemId);

        // Assert
        assertNotNull(result);
        verify(itemRepository, times(1)).findById(itemId);
    }

    @Test
    void getItemById_WithInvalidId_ShouldThrowNoSuchElementException() {
        // Arrange
        Long itemId = 1L;

        when(itemRepository.findById(itemId)).thenReturn(Optional.empty());

        // Assert
        assertThrows(NoSuchElementException.class, () -> itemService.getItemById(itemId));
        verify(itemRepository, times(1)).findById(itemId);
    }

    @Test
    void updateItem_ShouldUpdateItem() {
        // Arrange
        Long itemId = 1L;
        ItemDto updatedItemDto = new ItemDto();
        updatedItemDto.setTitle("Updated Title");
        updatedItemDto.setContent("Updated Content");
        updatedItemDto.setCategory(new Category());
        updatedItemDto.setUrl("http://example.com");

        Item existingItem = new Item();

        when(itemRepository.findById(itemId)).thenReturn(Optional.of(existingItem));

        // Act
        itemService.updateItem(itemId, updatedItemDto);

        // Assert
        assertEquals(updatedItemDto.getTitle(), existingItem.getTitle());
        assertEquals(updatedItemDto.getContent(), existingItem.getContent());
        assertEquals(updatedItemDto.getCategory(), existingItem.getCategory());
        assertEquals(updatedItemDto.getUrl(), existingItem.getUrl());
        verify(itemRepository, times(1)).save(existingItem);
    }
}

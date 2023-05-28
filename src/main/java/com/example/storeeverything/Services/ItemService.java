package com.example.storeeverything.Services;

import com.example.storeeverything.Dtos.ItemDto;
import com.example.storeeverything.Dtos.SecurityUserDto;
import com.example.storeeverything.Entities.Category;
import com.example.storeeverything.Entities.Item;
import com.example.storeeverything.Repositories.CategoryRepository;
import com.example.storeeverything.Repositories.ItemRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    UserServiceImpl userService;

    public List<Item> getAllItems(){return  itemRepository.findAll();}

    public List<Item> getAllLoggedUserItems(){
        Long id = userService.getLoggedUserId();
        return itemRepository.findAllByUserId(id);
    }

    public void addNewItem(ItemDto itemDto){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Item item = new Item();
        item.setTitle(itemDto.getTitle());
        item.setContent(itemDto.getContent());
        item.setCategory(itemDto.getCategory());
        item.setUrl(itemDto.getUrl());
        item.setUser( userService.getLoggedUserEntity());
        item.setCreated(LocalDate.now().format(dateTimeFormatter));
        System.out.println(item);

        itemRepository.save(item);

    }

    public void deleteItem(Long id) {itemRepository.deleteById(id);}

    public Item getItemById(Long id){
        Item item = itemRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Nie znaleziono elementu"));
        return item;
    }

    public void updateItem(Long id, ItemDto newItem){
        Item item = getItemById(id);
        item.setTitle(newItem.getTitle());
        item.setCategory(newItem.getCategory());
        item.setUrl(newItem.getUrl());
        item.setContent(newItem.getContent());
        itemRepository.save(item);
    }
}

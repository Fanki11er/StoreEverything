package com.example.storeeverything.Services;

import com.example.storeeverything.Entities.Category;
import com.example.storeeverything.Entities.Item;
import com.example.storeeverything.Repositories.CategoryRepository;
import com.example.storeeverything.Repositories.ItemRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;

    public List<Item> getAllItems(){return  itemRepository.findAll();}

    public void addNewItem(Item item){
        itemRepository.save(item);
    }

    public void deleteItem(Long id) {itemRepository.deleteById(id);}
}

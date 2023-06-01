package com.example.storeeverything.Services;

import com.example.storeeverything.Dtos.LinkedItemDto;
import com.example.storeeverything.Entities.Item;
import com.example.storeeverything.Entities.LinkedItem;
import com.example.storeeverything.Repositories.ItemRepository;
import com.example.storeeverything.Repositories.LinkedItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class LinkedItemsService {

    @Autowired
    private LinkedItemRepository linkedItemRepository;

    @Autowired
    private ItemRepository itemRepository;

   public String checkIfLinkedItemExists(Long id){
        LinkedItem item =  linkedItemRepository.findLinkedItemBySourceId(id);
        if(item != null){
            return "/SharedLinks/" + item.getId();
        }
        return "";
    }

    public Item getLinkedItem(Long id){
        LinkedItem linkedItem = linkedItemRepository.findById(id).orElse(null);
        if(linkedItem == null){
            return  null;
        }
        return  linkedItem.getSource();
    }

    public String addLinkedItem(LinkedItemDto dto){
        Item source = itemRepository.findById(dto.getItemId()).orElseThrow(()-> new NoSuchElementException("Nie znaleziono elementu"));
        LinkedItem item = new LinkedItem();
        item.setSource(source);
        //LinkedItem save = linkedItemRepository.save(item);
        linkedItemRepository.save(item);
        return  "/SharedLinks/" + item.getId();
    }
}

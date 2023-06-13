package com.example.storeeverything.Services;

import com.example.storeeverything.Dtos.ShareItemDto;
import com.example.storeeverything.Entities.Item;
import com.example.storeeverything.Entities.SharedItem;
import com.example.storeeverything.Entities.User;
import com.example.storeeverything.Repositories.ItemRepository;
import com.example.storeeverything.Repositories.SharedItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class SharedItemsService {

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    SharedItemsRepository sharedItemsRepository;
    @Autowired
    MailService mailService;

    public List<SharedItem> getAllLoggedUserSharedItems(){
        Long id = userService.getLoggedUserId();
        return sharedItemsRepository.findAllByUserId(id);
    }

    public void addSharedItem(ShareItemDto dto){
        User user = userService.getUserEntityById(dto.getUserId());
        Item item = itemRepository.findById(dto.getItemId()).orElseThrow(()-> new NoSuchElementException("Nie znaleziono informacji"));
        SharedItem sharedItem =new SharedItem();
        sharedItem.setSource(item);
        sharedItem.setUser(user);
        sharedItemsRepository.save(sharedItem);
        try{
            mailService.sendEmail(user.getFirstName() + " " + user.getSurname());
        }
        catch (IOException ex){
            System.out.println(ex.getMessage());
        }


    }

    public Item getSourceItemById(Long id){
        SharedItem sharedItem = sharedItemsRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Nie znaleziono elementu"));
        return sharedItem.getSource();
    }
}

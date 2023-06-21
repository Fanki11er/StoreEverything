package com.example.storeeverything.Controllers;
import com.example.storeeverything.Dtos.ItemDto;
import com.example.storeeverything.Dtos.LinkedItemDto;
import com.example.storeeverything.Dtos.UserDto;
import com.example.storeeverything.Dtos.ShareItemDto;
import com.example.storeeverything.Entities.Item;
import com.example.storeeverything.Services.*;
import com.example.storeeverything.Utils.Sort;
import com.example.storeeverything.Utils.SortBy;
import com.example.storeeverything.Utils.SortCookie;
import com.example.storeeverything.Utils.SortOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("/App/Items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private LinkedItemsService linkedItemsService;

    @GetMapping("")
    public String Items(Model model, HttpServletRequest request) throws JsonProcessingException, UnsupportedEncodingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Long userId =  userService.getLoggedUserId();
        SortCookie sortCookie = new SortCookie();
        Sort sort = new Sort();
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            String cookieName = "SE-USER-" + userId;
            for(int i = 0; i< cookies.length; i++){

                if(cookies[i].getName().equals( cookieName) ){
                   String cookieValue = URLDecoder.decode(cookies[i].getValue(), "UTF-8");
                    sortCookie = objectMapper.readValue(cookieValue, SortCookie.class);
                    break;
                }
            }
        }

        String loggedUserRole = userService.getLoggedUserRole();
        List<Item> items = itemService.getAllLoggedUserItems();
        sort.sortList(items, sortCookie.getSortBy(), sortCookie.getSortOrder());
        model.addAttribute("items", items);
        model.addAttribute("path", "Information");
        model.addAttribute("loggedUserRole", loggedUserRole);
        model.addAttribute("sort", sortCookie);
        model.addAttribute("sortBy", SortBy.values());
        model.addAttribute("orderBy", SortOrder.values());
        return "Items";
    }

    @PostMapping("/Sort")
    public String sortItems( @ModelAttribute("sortCookie") SortCookie sortCookie, Model model, HttpServletResponse response) throws JsonProcessingException, UnsupportedEncodingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Long userId = userService.getLoggedUserId();
        String loggedUserRole = userService.getLoggedUserRole();
        model.addAttribute("path", "Information");
        model.addAttribute("loggedUserRole", loggedUserRole);
        String value = objectMapper.writeValueAsString(sortCookie);
        value = URLEncoder.encode(value, "UTF-8");
        Cookie cookie = new Cookie("SE-USER-" + userId, value);
        response.addCookie(cookie);
        return "redirect:/App/Items";
    }

    @GetMapping("/New")
    public String createItem(Model model) {
        String loggedUserRole = userService.getLoggedUserRole();
        model.addAttribute("item", new ItemDto());
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("path", "Information");
        model.addAttribute("loggedUserRole", loggedUserRole);
        return "AddItemForm";
    }

    @PostMapping("/New/Save")
    public String saveItem(@Valid @ModelAttribute("item") ItemDto itemDto, BindingResult bindingResult, Model model) {
        String loggedUserRole = userService.getLoggedUserRole();
        model.addAttribute("path", "Information");
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("loggedUserRole", loggedUserRole);
        if (bindingResult.hasErrors()) {
            return "AddItemForm";
        }
        itemService.addNewItem(itemDto);
        return "redirect:/App/Items";
    }

    @PostMapping("/Delete/{id}")
    public String deleteItem(@PathVariable("id") Long id, Model model) {
        model.addAttribute("path", "Information");
        itemService.deleteItem(id);
        return "redirect:/App/Items";
    }

    @GetMapping("/{id}")
    public String itemDetails(@PathVariable("id") Long id, Model model) {
        String loggedUserRole = userService.getLoggedUserRole();
        Item item = itemService.getItemById(id);
        model.addAttribute("item", item);
        model.addAttribute("path", "Information");
        model.addAttribute("loggedUserRole", loggedUserRole);
        return "itemDetails";
    }

    @GetMapping("/Edit/{id}")
    public String editItemForm(@PathVariable("id") Long id, Model model) {
        String loggedUserRole = userService.getLoggedUserRole();
        Item item = itemService.getItemById(id);
        model.addAttribute("item", item);
        model.addAttribute("newItem", item);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("path", "Information");
        model.addAttribute("loggedUserRole", loggedUserRole);
        return "editItemForm";
    }



    @PostMapping("/Edit/doEdit/{id}")
    public String editItem(@PathVariable("id") Long id, @Valid @ModelAttribute("newItem") ItemDto newItem, BindingResult bindingResult, Model model) {
        Item item = itemService.getItemById(id);
        String loggedUserRole = userService.getLoggedUserRole();
        model.addAttribute("path", "Information");
        model.addAttribute("item", item);
        model.addAttribute("newItem", item);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("loggedUserRole", loggedUserRole);
        if (bindingResult.hasErrors()) {
            return "redirect:/App/Items/Edit/{id}?error";
        }
        itemService.updateItem(id, newItem);
        return "redirect:/App/Items/" + id;
    }

    @GetMapping("/Share/{id}")
    public String shareItem(@PathVariable("id") Long id, Model model) {
        String loggedUserRole = userService.getLoggedUserRole();
        List<UserDto> users = userService.loadUsers();
        String link = linkedItemsService.checkIfLinkedItemExists(id);
        model.addAttribute("path", "Information");
        model.addAttribute("loggedUserRole", loggedUserRole);
        model.addAttribute("users", users);
        model.addAttribute("sharedItem", new ShareItemDto());
        model.addAttribute("linkedItem", new LinkedItemDto());
        model.addAttribute("link", link);
        model.addAttribute("itemId", id);
        return "share";
    }
}

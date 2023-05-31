package com.example.storeeverything.Utils;

import com.example.storeeverything.Entities.Item;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sort {


    public void sortList(List<Item> items, SortBy sortBy, SortOrder sortOrder){
        switch (sortBy){

            case NAZWA:{
                sortByName(items, sortOrder);
                break;
            }
            case KATEGORIA:{
                sortByCategory(items, sortOrder);
                break;
            }
            case DATA: {
                sortByDate(items,sortOrder);
                break;
            }
        }
    }

    private void sortByDate(List<Item> items, SortOrder sortOrder) {
        Comparator<Item> comparator = Comparator.comparing(Item::getCreated);
        if (sortOrder == SortOrder.MALEJĄCO) {
            comparator = comparator.reversed();
        }
        Collections.sort(items, comparator);
    }

    private void sortByCategory(List<Item> items, SortOrder sortOrder) {
        Comparator<Item> comparator = Comparator.comparing((Item item) -> item.getCategory().getName());
        if (sortOrder == SortOrder.MALEJĄCO) {
            comparator = comparator.reversed();
        }
        Collections.sort(items, comparator);
    }

    private void sortByName(List<Item> items, SortOrder sortOrder) {
        Comparator<Item> comparator = Comparator.comparing(Item::getTitle);
        if (sortOrder == SortOrder.MALEJĄCO) {
            comparator = comparator.reversed();
        }
        Collections.sort(items, comparator);
    }
}

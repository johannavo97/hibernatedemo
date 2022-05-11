package com.hibernate.dao;

import com.hibernate.models.Item;

import java.util.List;

public interface ItemI {

    void createItem(Item item);
    int deleteItem(Item item);
    void updateItem(Item item);
    List<Item> getItems();
}

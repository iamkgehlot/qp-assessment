package com.grocery.grocerybooking.service;

import com.grocery.grocerybooking.entity.GroceryItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    public List<GroceryItem> addGroceries(List<GroceryItem> groceryItem);
    public Optional<GroceryItem> getGroceryById(Long groceryItemId);
    public List<GroceryItem> getAllGrocery();
    public GroceryItem updateGroceryItem(Long id,GroceryItem updatedItem);
    public void deleteGroceryItem(Long groceryItemId);
    public GroceryItem updateInventory(Long groceryItemId,Integer stockQuantity);


}

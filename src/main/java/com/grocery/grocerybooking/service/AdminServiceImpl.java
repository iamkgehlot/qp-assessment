package com.grocery.grocerybooking.service;

import com.grocery.grocerybooking.entity.GroceryItem;
import com.grocery.grocerybooking.exceptions.GroceryItemNotFoundException;
import com.grocery.grocerybooking.exceptions.InvalidInventoryLevelException;
import com.grocery.grocerybooking.repository.GroceryItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{

    @Autowired
    GroceryItemRepo groceryItemRepo;

    @Override
    public List<GroceryItem> addGroceries(List<GroceryItem> groceryItem) {
        return groceryItemRepo.saveAll(groceryItem);
    }

    @Override
    public Optional<GroceryItem> getGroceryById(Long groceryItemId) {
        return groceryItemRepo.findById(groceryItemId);
    }

    @Override
    public List<GroceryItem> getAllGrocery() {
        return groceryItemRepo.findAll();
    }

    @Override
    public GroceryItem updateGroceryItem(Long groceryItemId,GroceryItem updatedItem ) {
        GroceryItem existingItem = groceryItemRepo.findById(groceryItemId)
                .orElseThrow(() -> new GroceryItemNotFoundException("Grocery item not found with id " + groceryItemId));

        // Update the necessary fields
        existingItem.setName(updatedItem.getName());
        existingItem.setPrice(updatedItem.getPrice());
        existingItem.setDescription((updatedItem.getDescription()));

        // Save the updated item
        return groceryItemRepo.save(existingItem);
    }

    @Override
    public void deleteGroceryItem(Long groceryItemId) {
        groceryItemRepo.deleteById(groceryItemId);
    }

    @Override
    public GroceryItem updateInventory(Long groceryItemId,Integer stockQuantity) {
        if(stockQuantity<0){
            throw new InvalidInventoryLevelException("Inventory level cannot be negative.");
        }
        GroceryItem existingItem = groceryItemRepo.findById(groceryItemId)
                .orElseThrow(() -> new GroceryItemNotFoundException("Grocery item not found with id " + groceryItemId));
        existingItem.setStockQuantity(stockQuantity);
        return groceryItemRepo.save(existingItem);

    }
}

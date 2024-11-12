package com.grocery.grocerybooking.controller;

import com.grocery.grocerybooking.entity.GroceryItem;
import com.grocery.grocerybooking.service.AdminService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/groceries")
    public ResponseEntity<List<GroceryItem>> addGroceries(@RequestBody List<GroceryItem> groceryItem){
        return new ResponseEntity<>(adminService.addGroceries(groceryItem), HttpStatus.CREATED);
    }
    @GetMapping("/groceries/{groceryItemId}")
    public ResponseEntity<Optional<GroceryItem>> getAllGrocery(@PathVariable Long groceryItemId) {
        Optional<GroceryItem> groceryItem = adminService.getGroceryById(groceryItemId);
        return groceryItem.isPresent()
                ? new ResponseEntity<>(groceryItem, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/groceries")
    public ResponseEntity<List<GroceryItem>> getAllGrocery(){
        return new ResponseEntity<>(adminService.getAllGrocery(),HttpStatus.FOUND);
    }

    @PutMapping("/groceries/{groceryItemId}")
    public ResponseEntity<GroceryItem> updateGroceryItem(@PathVariable Long groceryItemId,@RequestBody GroceryItem groceryItem){
        return new ResponseEntity<>(adminService.updateGroceryItem(groceryItemId,groceryItem), HttpStatus.CREATED);
    }

    @DeleteMapping("/groceries/{groceryItemId}")
    public ResponseEntity<String> deleteGroceryItem(@PathVariable Long groceryItemId){
         adminService.deleteGroceryItem(groceryItemId);
         return new ResponseEntity<>("Item Deleted from Records", HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/groceries/{groceryItemId}")
    public ResponseEntity<GroceryItem> updateInventory(@PathVariable Long groceryItemId,@RequestBody Integer stockQuantity){
        GroceryItem updatedItem = adminService.updateInventory(groceryItemId, stockQuantity);

        return new ResponseEntity<>(updatedItem,HttpStatus.ACCEPTED);
    }

}

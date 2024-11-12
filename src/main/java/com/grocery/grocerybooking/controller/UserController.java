package com.grocery.grocerybooking.controller;

import com.grocery.grocerybooking.dto.OrderRequest;
import com.grocery.grocerybooking.entity.GroceryItem;
import com.grocery.grocerybooking.entity.OrderItem;
import com.grocery.grocerybooking.entity.Orders;
import com.grocery.grocerybooking.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/get-grocery-item")
    public ResponseEntity<List<GroceryItem>> getGroceryItem(){
        return new ResponseEntity<>(userService.getAllGroceryItems(), HttpStatus.OK);
    }

    @PostMapping("/place-order/{userId}")
    public ResponseEntity<Orders>  placeOrder(@PathVariable  Long userId, @RequestBody List<OrderRequest> orderRequests){
        Orders savedOrder = userService.placeOrder(userId, orderRequests);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);

    }
}

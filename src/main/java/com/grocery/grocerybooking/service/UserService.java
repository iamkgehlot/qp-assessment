package com.grocery.grocerybooking.service;

import com.grocery.grocerybooking.dto.OrderRequest;
import com.grocery.grocerybooking.entity.GroceryItem;
import com.grocery.grocerybooking.entity.Orders;

import java.util.List;

public interface UserService {
     List<GroceryItem> getAllGroceryItems();
     Orders placeOrder(Long userId, List<OrderRequest> orderRequests);
}

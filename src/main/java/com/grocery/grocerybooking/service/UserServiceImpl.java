package com.grocery.grocerybooking.service;

import com.grocery.grocerybooking.dto.OrderRequest;
import com.grocery.grocerybooking.entity.GroceryItem;
import com.grocery.grocerybooking.entity.OrderItem;
import com.grocery.grocerybooking.entity.Orders;
import com.grocery.grocerybooking.repository.GroceryItemRepo;
import com.grocery.grocerybooking.repository.OrderItemRepo;
import com.grocery.grocerybooking.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    OrderItemRepo orderItemRepo;

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    GroceryItemRepo groceryItemRepo;

    @Override
    public List<GroceryItem> getAllGroceryItems() {
        return groceryItemRepo.findAll();
    }

    @Transactional
    public Orders placeOrder(Long userId, List<OrderRequest> orderRequests) {
        // Calculate total price
        double totalPrice = 0;
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderRequest orderRequest : orderRequests) {
            Optional<GroceryItem> groceryItemOpt = groceryItemRepo.findById(orderRequest.getGroceryItemId());
            if (!groceryItemOpt.isPresent()) {
                throw new IllegalArgumentException("Grocery item not found: " + orderRequest.getGroceryItemId());
            }

            GroceryItem groceryItem = groceryItemOpt.get();
            if (groceryItem.getStockQuantity() < orderRequest.getQuantity()) {
                throw new IllegalArgumentException("Insufficient stock for item: " + groceryItem.getName());
            }

            // Create OrderItem
            OrderItem orderItem = new OrderItem();
            orderItem.setGroceryItem(groceryItem);
            orderItem.setQuantity(orderRequest.getQuantity());
            orderItem.setPrice(groceryItem.getPrice());

            // Add to list and update total price
            orderItems.add(orderItem);
            totalPrice += orderItem.getPrice() * orderItem.getQuantity();

            // Update stock quantity of the grocery item
            groceryItem.setStockQuantity(groceryItem.getStockQuantity() - orderRequest.getQuantity());
            groceryItemRepo.save(groceryItem);
        }

        // Create the Order
        Orders order = new Orders();
        order.setUserId(userId);
        order.setOrderDate(LocalDate.now());
        order.setTotalPrice(totalPrice);
        order.setItems(orderItems);

        // Save the order and order items to the database
        Orders savedOrder = orderRepo.save(order);

        // Save order items
        for (OrderItem orderItem : orderItems) {
            orderItem.setOrder(savedOrder); // Set the order reference in the order item
            orderItemRepo.save(orderItem);
        }

        return savedOrder;

    }
}
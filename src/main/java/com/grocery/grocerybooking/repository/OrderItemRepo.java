package com.grocery.grocerybooking.repository;

import com.grocery.grocerybooking.entity.OrderItem;
import com.grocery.grocerybooking.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem,Long> {

}

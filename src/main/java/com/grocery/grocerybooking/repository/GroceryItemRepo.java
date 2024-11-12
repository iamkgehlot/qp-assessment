package com.grocery.grocerybooking.repository;

import com.grocery.grocerybooking.entity.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryItemRepo extends JpaRepository<GroceryItem,Long> {
}

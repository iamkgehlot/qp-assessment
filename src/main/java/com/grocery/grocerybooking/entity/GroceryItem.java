package com.grocery.grocerybooking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroceryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="grocery_item_id")
    private Long groceryItemId;
    private String name;
    private String description;
    private double price;
    //private String category;
    private Integer stockQuantity;
}

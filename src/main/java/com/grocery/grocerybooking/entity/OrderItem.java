package com.grocery.grocerybooking.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_item_id")
    private Long orderItemid;


    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonBackReference// Foreign key column to reference the order
    private Orders order;  // Reference to the parent order

    @ManyToOne
    @JoinColumn(name = "grocery_item_id")
    private GroceryItem groceryItem;

    private Integer quantity;  // Quantity of this item in the order
    private Double price;       // Price at the time of order


}

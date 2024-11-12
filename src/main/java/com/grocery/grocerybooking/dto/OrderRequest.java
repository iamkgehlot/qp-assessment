package com.grocery.grocerybooking.dto;

import lombok.Data;

@Data
public class OrderRequest {
    private Long groceryItemId;
    private Integer quantity;

}

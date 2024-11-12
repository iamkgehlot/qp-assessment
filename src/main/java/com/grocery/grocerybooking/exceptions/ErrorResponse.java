package com.grocery.grocerybooking.exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
public class ErrorResponse {
    HttpStatus httpStatus;
    String error;
}
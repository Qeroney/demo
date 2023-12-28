package com.example.demo.api.cart.dto;

import com.example.demo.api.product.dto.ProductDto;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class CartDto {

    UUID id;

    List<ProductDto> products;
}

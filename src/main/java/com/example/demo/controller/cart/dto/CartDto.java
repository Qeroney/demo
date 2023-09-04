package com.example.demo.controller.cart.dto;

import com.example.demo.controller.product.dto.ProductDto;
import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CartDto {

    private UUID id;

    private List<ProductDto> products;
}

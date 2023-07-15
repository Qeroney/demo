package com.example.demo.controller.Product;


import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CreateProductDto {

    private String title;

    private Long price;

    private UUID categoryId;
}

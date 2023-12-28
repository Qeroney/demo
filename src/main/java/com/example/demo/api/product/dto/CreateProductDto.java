package com.example.demo.api.product.dto;

import lombok.*;

import java.util.UUID;

@Value
@Builder
public class CreateProductDto {

    String title;

    Long price;

    UUID categoryId;
}

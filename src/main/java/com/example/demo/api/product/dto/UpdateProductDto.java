package com.example.demo.api.product.dto;

import lombok.*;

import java.util.UUID;

@Value
@Builder
public class UpdateProductDto {

    String title;

    Long price;

    UUID categoryId;
}

package com.example.demo.api.product.dto;

import com.example.demo.api.category.dto.CategoryDto;
import lombok.*;

import java.util.UUID;

@Value
@Builder
public class ProductDto {

    UUID id;

    String title;

    Long price;

    CategoryDto category;
}

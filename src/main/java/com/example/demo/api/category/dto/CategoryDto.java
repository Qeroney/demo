package com.example.demo.api.category.dto;

import lombok.*;

import java.util.UUID;

@Value
@Builder
public class CategoryDto {
    UUID id;

    String title;
}

package com.example.demo.controller.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ProductDto {
    private UUID id;

    private String title;

    private Long price;

}

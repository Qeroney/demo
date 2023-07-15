package com.example.demo.controller.Product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateProductDto {

    private String title;

    private Long price;
}

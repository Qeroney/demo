package com.example.demo.controller.product.dto;

import com.example.demo.controller.category.dto.CategoryDto;
import com.example.demo.model.Category;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProductDto {

    private UUID id;

    private String title;

    private Long price;

    private CategoryDto category;

}

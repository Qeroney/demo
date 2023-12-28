package com.example.demo.api.category.dto;

import lombok.*;

@Value
@Builder
public class SearchCategoryDto {

    String productTitle;

    String categoryTitle;
}

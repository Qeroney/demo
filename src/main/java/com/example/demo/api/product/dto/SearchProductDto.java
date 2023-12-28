package com.example.demo.api.product.dto;

import lombok.*;

@Value
@Builder
public class SearchProductDto {

    String productTitle;

    String categoryTitle;
}

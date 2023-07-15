package com.example.demo.controller.product.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SearchProductDto {

    private String productTitle;

    private String categoryTitle;
}

package com.example.demo.controller.category.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SearchCategoryDto {

    private String productTitle;

    private String categoryTitle;
}

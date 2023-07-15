package com.example.demo.service.category.argument;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SearchCategoryArgument {

    private String categoryTitle;

    private String productTitle;

}

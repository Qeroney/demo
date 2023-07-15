package com.example.demo.service.argument.Product;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SearchProductArgument {

    private String productTitle;

    private String categoryTitle;
}

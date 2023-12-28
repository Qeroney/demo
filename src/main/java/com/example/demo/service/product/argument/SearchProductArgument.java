package com.example.demo.service.product.argument;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SearchProductArgument {

    String productTitle;

    String categoryTitle;
}

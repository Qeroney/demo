package com.example.demo.service.argument.Product;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateProductArgument {
    String title;

    Long price;
}

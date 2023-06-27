package com.example.demo.service.argument;

import com.example.demo.model.Category;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateProductArgument {

    String title;

    Long price;

    Category category;
}

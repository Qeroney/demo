package com.example.demo.service.category.argument;

import com.example.demo.model.Category;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateCategoryArgument {
    String title;

    Category category;
}

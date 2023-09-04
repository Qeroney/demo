package com.example.demo.action.create;

import com.example.demo.model.Category;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class CreateProductActionArgument {

    String title;

    Long price;

    UUID categoryId;
}

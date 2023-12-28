package com.example.demo.service.category;

import com.example.demo.model.Category;
import com.example.demo.service.category.argument.CreateCategoryArgument;
import com.example.demo.service.category.argument.SearchCategoryArgument;
import com.example.demo.service.category.argument.UpdateCategoryArgument;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    List<Category> list(SearchCategoryArgument argument);

    Category create(CreateCategoryArgument categoryArgument);

    Category getExisting(UUID id);

    Category update(UUID id, UpdateCategoryArgument categoryArgument);

    void delete(UUID id);
}

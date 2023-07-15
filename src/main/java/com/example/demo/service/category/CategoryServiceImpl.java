package com.example.demo.service.category;

import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.category.argument.CreateCategoryArgument;
import com.example.demo.service.category.argument.SearchCategoryArgument;
import com.example.demo.service.category.argument.UpdateCategoryArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Override
    public List<Category> list(SearchCategoryArgument argument) {
        return repository.findAll();
    }

    @Override
    public Category create(CreateCategoryArgument categoryArgument) {
        return repository.save(Category.builder()
                .title(categoryArgument.getTitle())
                .build());
    }

    @Override
    public Category getExisting(UUID id) {
        return repository.findById(id)
                .orElseThrow(()->new RuntimeException("категория не найдена"));
    }

    @Override
    public Category update(UUID id, UpdateCategoryArgument categoryArgument) {
        Category category = getExisting(id);

        category.setTitle(categoryArgument.getTitle());

        return repository.save(category);
    }

    @Override
    public void delete(UUID id) {
     repository.deleteById(id);
    }
}

package com.example.demo.controller.category;

import com.example.demo.controller.category.dto.CreateCategoryDto;
import com.example.demo.controller.category.dto.SearchCategoryDto;
import com.example.demo.controller.category.dto.UpdateCategoryDto;
import com.example.demo.controller.category.dto.CategoryDto;
import com.example.demo.model.Category;
import com.example.demo.service.category.CategoryService;
import com.example.demo.service.category.argument.SearchCategoryArgument;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.demo.controller.category.mapper.CategoryMapper.CATEGORY_MAPPER;

@RestController
@RequestMapping("category")
@RequiredArgsConstructor
public class CategoryController {
    CategoryService categoryService;

    @GetMapping("list")
        public List<CategoryDto> list(SearchCategoryDto dto){
        SearchCategoryArgument argument = CATEGORY_MAPPER.toSearchCategory(dto);

       return categoryService.list(argument)
               .stream()
               .map(CATEGORY_MAPPER::toDto)
               .collect(Collectors.toList());
    }

    @PostMapping("create")
    public CategoryDto create(@RequestBody CreateCategoryDto dto){

        Category category = categoryService.create(CATEGORY_MAPPER.toCreateCategory(dto));

        return CATEGORY_MAPPER.toDto(category);
    }

    @PutMapping("update/{id}")
    public CategoryDto update(@PathVariable UUID id, @RequestBody UpdateCategoryDto dto){

        Category category = categoryService.update(id, CATEGORY_MAPPER.toUpdateCategoryArgument(dto));

        return CATEGORY_MAPPER.toDto(category);

    }
    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable UUID id){
       categoryService.delete(id);
    }
}

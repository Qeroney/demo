package com.example.demo.api.category.mapper;

import com.example.demo.api.category.dto.CategoryDto;
import com.example.demo.api.category.dto.CreateCategoryDto;
import com.example.demo.api.category.dto.SearchCategoryDto;
import com.example.demo.api.category.dto.UpdateCategoryDto;
import com.example.demo.model.Category;
import com.example.demo.service.category.argument.CreateCategoryArgument;
import com.example.demo.service.category.argument.SearchCategoryArgument;
import com.example.demo.service.category.argument.UpdateCategoryArgument;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {

    CategoryMapper CATEGORY_MAPPER = Mappers.getMapper(CategoryMapper.class);

    SearchCategoryArgument toSearchCategory(SearchCategoryDto dto);

    CreateCategoryArgument toCreateCategory(CreateCategoryDto dto);

    CategoryDto toDto(Category category);

    UpdateCategoryArgument toUpdateCategoryArgument(UpdateCategoryDto dto);
}

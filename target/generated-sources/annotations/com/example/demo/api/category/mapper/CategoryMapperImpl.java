package com.example.demo.api.category.mapper;

import com.example.demo.api.category.dto.CategoryDto;
import com.example.demo.api.category.dto.CreateCategoryDto;
import com.example.demo.api.category.dto.SearchCategoryDto;
import com.example.demo.api.category.dto.UpdateCategoryDto;
import com.example.demo.model.Category;
import com.example.demo.service.category.argument.CreateCategoryArgument;
import com.example.demo.service.category.argument.SearchCategoryArgument;
import com.example.demo.service.category.argument.UpdateCategoryArgument;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-28T19:38:11+1000",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.9 (Eclipse Adoptium)"
)
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public SearchCategoryArgument toSearchCategory(SearchCategoryDto dto) {
        if ( dto == null ) {
            return null;
        }

        SearchCategoryArgument.SearchCategoryArgumentBuilder searchCategoryArgument = SearchCategoryArgument.builder();

        searchCategoryArgument.categoryTitle( dto.getCategoryTitle() );
        searchCategoryArgument.productTitle( dto.getProductTitle() );

        return searchCategoryArgument.build();
    }

    @Override
    public CreateCategoryArgument toCreateCategory(CreateCategoryDto dto) {
        if ( dto == null ) {
            return null;
        }

        CreateCategoryArgument.CreateCategoryArgumentBuilder createCategoryArgument = CreateCategoryArgument.builder();

        createCategoryArgument.title( dto.getTitle() );

        return createCategoryArgument.build();
    }

    @Override
    public CategoryDto toDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto.CategoryDtoBuilder categoryDto = CategoryDto.builder();

        categoryDto.id( category.getId() );
        categoryDto.title( category.getTitle() );

        return categoryDto.build();
    }

    @Override
    public UpdateCategoryArgument toUpdateCategoryArgument(UpdateCategoryDto dto) {
        if ( dto == null ) {
            return null;
        }

        UpdateCategoryArgument.UpdateCategoryArgumentBuilder updateCategoryArgument = UpdateCategoryArgument.builder();

        updateCategoryArgument.title( dto.getTitle() );

        return updateCategoryArgument.build();
    }
}

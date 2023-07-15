package com.example.demo.action.create;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.service.category.CategoryService;
import com.example.demo.service.product.ProductService;
import com.example.demo.service.product.argument.CreateProductArgument;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;


public class CreateProductActionTest {

     private final ProductService productService = Mockito.mock(ProductService.class);

     private final CategoryService categoryService = Mockito.mock(CategoryService.class);

     private final CreateProductAction action = new CreateProductAction(productService,categoryService);

    @Test
    public void execute() {

        // Arrange

        Product expectedProduct = Mockito.mock(Product.class);
        UUID categoryId = UUID.randomUUID();

        CreateProductActionArgument argument = CreateProductActionArgument.builder()
                .title("test")
                .categoryId(categoryId)
                .price(0l)
                .build();

        Category category = Category.builder()
                .id(categoryId)
                .title("test")
                .products(new ArrayList<>())
                .build();

        Mockito.when(categoryService.getExisting(categoryId)).thenReturn(category);
        Mockito.when(productService.create(any())).thenReturn(expectedProduct);

        // Act

        Product execute = action.execute(argument);

        // Assert

        ArgumentCaptor<UUID> categoryIdCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<CreateProductArgument> productCaptor = ArgumentCaptor.forClass(CreateProductArgument.class);

        Mockito.verify(categoryService,Mockito.only()).getExisting(categoryIdCaptor.capture());
        Mockito.verify(productService,Mockito.only()).create(productCaptor.capture());

        CreateProductArgument expectedArgument = CreateProductArgument.builder()
                .title("test")
                .price(0l)
                .category(Category.builder()
                        .id(categoryId)
                        .title("test")
                        .products(new ArrayList<>())
                        .build())
                .build();

        Assertions.assertEquals(execute,expectedProduct);

        assertThat(categoryIdCaptor.getValue())
                .usingRecursiveComparison()
                .isEqualTo(categoryId);

        assertThat(productCaptor.getValue())
                .usingRecursiveComparison()
                .isEqualTo(expectedArgument);


    }
}

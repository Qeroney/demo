package com.example.demo.action.update;

import com.example.demo.action.create.CreateProductActionArgument;
import com.example.demo.action.update.UpdateProductAction;
import com.example.demo.action.update.UpdateProductActionArgument;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.service.category.CategoryService;
import com.example.demo.service.product.ProductService;
import com.example.demo.service.product.argument.CreateProductArgument;
import com.example.demo.service.product.argument.UpdateProductArgument;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static net.bytebuddy.matcher.ElementMatchers.any;

public class UpdateProductActionTest {

    private final ProductService productService = Mockito.mock(ProductService.class);

    private final CategoryService categoryService = Mockito.mock(CategoryService.class);

    private  final UpdateProductAction action = new UpdateProductAction(productService,categoryService);

    @Test
    public void execute(){

        // Arrange

        Product expectedProduct = Mockito.mock(Product.class);
        UUID categoryId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();

        UpdateProductActionArgument argument = UpdateProductActionArgument.builder()
                .title("test")
                .categoryId(categoryId)
                .price(2l)
                .build();

        Category category = Category.builder()
                .id(categoryId)
                .title("test")
                .products(new ArrayList<>())
                .build();

        Mockito.when(categoryService.getExisting(categoryId)).thenReturn(category);
        Mockito.when(productService.update(any(UUID.class),any(UpdateProductArgument.class)))
                .thenReturn(expectedProduct);

        // Act

        Product execute = action.execute(productId,argument);

        // Assert

        ArgumentCaptor<UUID> categoryIdCaptor = ArgumentCaptor.forClass(UUID.class);
        ArgumentCaptor<UpdateProductArgument> updateProductArgumentCaptor = ArgumentCaptor.forClass(UpdateProductArgument.class);
        ArgumentCaptor<UUID> productIdCaptor = ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(categoryService,Mockito.only()).getExisting(categoryIdCaptor.capture());

        Mockito.verify(productService, Mockito.only())
                .update(productIdCaptor.capture(),updateProductArgumentCaptor.capture());

        UpdateProductArgument expectedArgument = UpdateProductArgument.builder()
                .title("test")
                .price(2l)
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

        assertThat(productIdCaptor.getValue())
                .usingRecursiveComparison()
                .isEqualTo(productId);

        assertThat(updateProductArgumentCaptor.getValue())
                .usingRecursiveComparison()
                .isEqualTo(expectedArgument);


    }
}

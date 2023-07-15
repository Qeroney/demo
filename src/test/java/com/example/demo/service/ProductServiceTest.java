package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.product.ProductService;
import com.example.demo.service.product.ProductServiceImpl;
import com.example.demo.service.product.argument.CreateProductArgument;
import com.example.demo.service.product.argument.SearchProductArgument;
import com.example.demo.service.product.argument.UpdateProductArgument;
import com.querydsl.core.types.Predicate;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;


public class ProductServiceTest {

    private final ProductRepository productRepository = mock(ProductRepository.class);

    private final ProductService service = new ProductServiceImpl(productRepository);

    @Test
    public void list(){

        // Arrange

        SearchProductArgument argument = SearchProductArgument.builder()
                .productTitle("test1")
                .categoryTitle("test2")
                .build();

        List<Product> expectedList = new ArrayList<>();
        Mockito.when(productRepository.findAll(any(Predicate.class))).thenReturn(expectedList);

        // Act

        List<Product> list = service.list(argument);

        // Assert

        ArgumentCaptor<Predicate> predicateArgumentCaptor = ArgumentCaptor.forClass(Predicate.class);
        Mockito.verify(productRepository,Mockito.only()).findAll(predicateArgumentCaptor.capture());
        Predicate capturedPredicate = predicateArgumentCaptor.getValue();

        Assertions.assertThat(capturedPredicate.toString())
                .isEqualTo("containsIc(product.title,test1) && containsIc(product.category.title,test2)");
        Assertions.assertThat(list).usingRecursiveComparison()
                .withStrictTypeChecking()
                .isEqualTo(expectedList);
    }
    @Test
    public void create(){

        // Arrange

        Product product = Mockito.mock(Product.class);

        CreateProductArgument argument = CreateProductArgument.builder()
                .title("картофанчик")
                .price(0l)
                .category(Category.builder()
                        .title("преколдесы")
                        .build())
                .build();

        Mockito.when(productRepository.save(any())).thenReturn(product);

        // Act

        Product actual = service.create(argument);

        // Assert

        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);

        Mockito.verify(productRepository).save(productArgumentCaptor.capture());
        Product capturedProduct = productArgumentCaptor.getValue();

        Product expectedCapturedProduct = Product.builder()
                .title("картофанчик")
                .price(0l)
                .category(Category.builder()
                        .title("преколдесы")
                        .build())
                .build();

        Assertions.assertThat(capturedProduct)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .isEqualTo(expectedCapturedProduct);

        Assertions.assertThat(actual).isEqualTo(product);

    }
    @Test
    public void update(){

        // Arrange

        UUID id = UUID.randomUUID();
        Product product = Mockito.mock(Product.class);

        UpdateProductArgument argument = UpdateProductArgument.builder()
                .title("Хлеб")
                .price(5l)
                .category(Category.builder()
                        .title("Мучное")
                        .build())
                .build();

        Mockito.when(productRepository.findById(id)).thenReturn(Optional.of(Product.builder()
                .id(id)
                .title("test")
                .price(0l)
                .category(Category.builder()
                        .title("Мучное")
                        .build())
                .build()));
        Mockito.when(productRepository.save(any())).thenReturn(product);

        // Act

        Product actual = service.update(id,argument);

        // Assert

        ArgumentCaptor<Product> productArgumentCaptor = ArgumentCaptor.forClass(Product.class);
        ArgumentCaptor<UUID> idCaptor = ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(productRepository).findById(idCaptor.capture());
        Mockito.verify(productRepository).save(productArgumentCaptor.capture());

        Product expectedCapturedProduct = Product.builder()
                .id(id)
                .title("Хлеб")
                .price(5l)
                .category(Category.builder()
                        .title("Мучное")
                        .build())
                .build();

        Assertions.assertThat(idCaptor.getValue()).isEqualTo(id);
        Assertions.assertThat(productArgumentCaptor.getValue())
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .isEqualTo(expectedCapturedProduct);

        Assertions.assertThat(actual).isEqualTo(product);

    }
    @Test
    public void getExisting(){

        // Arrange

        Product product = Mockito.mock(Product.class);
        UUID id = UUID.randomUUID();

        Mockito.when(productRepository.findById(any())).thenReturn(Optional.of(product));

        // Act

        Product actual = service.getExisting(id);

        // Assert

        ArgumentCaptor<UUID> captor = ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(productRepository).findById(captor.capture());


        Assertions.assertThat(captor.getValue())
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .isEqualTo(id);
        Assertions.assertThat(actual).isEqualTo(product);

    }
    @Test
    public void delete(){

        // Arrange

        UUID id = UUID.randomUUID();

        // Act

         service.delete(id);

        // Assert

        ArgumentCaptor<UUID> captor = ArgumentCaptor.forClass(UUID.class);

        Mockito.verify(productRepository).deleteById(captor.capture());

        Assertions.assertThat(captor.getValue())
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .isEqualTo(id);

    }
}

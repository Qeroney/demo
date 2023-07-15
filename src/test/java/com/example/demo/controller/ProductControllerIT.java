package com.example.demo.controller;

import com.example.demo.controller.category.dto.CategoryDto;
import com.example.demo.controller.product.dto.CreateProductDto;
import com.example.demo.controller.product.dto.ProductDto;
import com.example.demo.controller.product.dto.SearchProductDto;
import com.example.demo.controller.product.dto.UpdateProductDto;
import com.example.demo.model.CustomUser;
import com.example.demo.model.Role;
import com.example.demo.security.JwtService;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.google.common.collect.Lists;
import com.jupiter.tools.spring.test.postgres.annotation.meta.EnablePostgresIntegrationTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebClient
@EnablePostgresIntegrationTest
class ProductControllerIT {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserDetailsService userDetailsService;

    private final UserDetails userDetails = CustomUser.builder()
            .email("test")
            .password("")
            .role(Role.USER)
            .build();

    @BeforeEach
    void setup() {
        Mockito.when(jwtService.extractUsername(any())).thenReturn("test");
        Mockito.when(userDetailsService.loadUserByUsername(any())).thenReturn(userDetails);
        Mockito.when(jwtService.isTokenValid(any(String.class),
                any(UserDetails.class))).thenReturn(true);

    }

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "datasets/api/product/list.json")
     public void list() {

        // Arrange

        SearchProductDto dto = SearchProductDto.builder()
                .productTitle("Картошечка")
                .categoryTitle("Овощи")
                .build();
        // Act

        List<ProductDto> responseBody = webTestClient.get()
                .uri(uriBuilder -> uriBuilder.path("/product/list")
                        .queryParam("productTitle", dto.getProductTitle())
                        .queryParam("categoryTitle", dto.getCategoryTitle())
                        .build())
                .header("Authorization", "Bearer token")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(ProductDto.class)
                .returnResult()
                .getResponseBody();

        // Assert

        List<ProductDto> expectedBody = Lists.newArrayList(ProductDto.builder()
                .id(UUID.fromString("b518fab0-302d-43de-b342-8026d9bce65a"))
                .title("Картошечка")
                .price(234L)
                .category(CategoryDto.builder()
                        .id(UUID.fromString("824c60d2-6c72-4822-9adf-a7e3b53938b8"))
                        .title("Овощи")
                        .build())
                .build());

        Assertions.assertThat(responseBody)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .isEqualTo(expectedBody);

    }

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "datasets/api/product/create.json")
    @ExpectedDataSet(value = "datasets/api/product/create_expected.json", orderBy = {"title"})
    public void create() {

        // Arrange

        CreateProductDto dto = CreateProductDto.builder()
                .title("Клубника")
                .price(55L)
                .categoryId(UUID.fromString("9b8af09b-4dee-41a6-b608-819f4978958e"))
                .build();

        // Act

        ProductDto responseBody = webTestClient.post()
                .uri(uriBuilder -> uriBuilder.path("/product/create/")
                        .build())
                .header("Authorization", "Bearer token")
                .bodyValue(dto)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(ProductDto.class)
                .returnResult()
                .getResponseBody();

        // Assert

        ProductDto expectedBody = ProductDto.builder()
                .title("Клубника")
                .price(55L)
                .category(CategoryDto.builder()
                        .id(UUID.fromString("9b8af09b-4dee-41a6-b608-819f4978958e"))
                        .title("Фрукты")
                        .build())
                .build();

        Assertions.assertThat(responseBody)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .ignoringFields("id")
                .isEqualTo(expectedBody);
    }

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "datasets/api/product/update.json")
    @ExpectedDataSet(value = "datasets/api/product/update_expected.json", orderBy = {"title"})
    public void update() {

        // Arrange

        UpdateProductDto dto = UpdateProductDto.builder()
                .title("Клубника")
                .price(50l)
                .categoryId(UUID.fromString("9b8af09b-4dee-41a6-b608-819f4978958e"))
                .build();

        // Act

        ProductDto response = webTestClient.put()
                .uri(uriBuilder -> uriBuilder.path("/product/update/a8c707fa-8a83-4ed0-bebf-1c407d68895f")
                        .build())
                .header("Authorization", "Bearer token")
                .bodyValue(dto)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(ProductDto.class)
                .returnResult()
                .getResponseBody();

        // Assert

        ProductDto expected = ProductDto.builder()
                .id(UUID.fromString("a8c707fa-8a83-4ed0-bebf-1c407d68895f"))
                .title("Клубника")
                .price(50l)
                .category(CategoryDto.builder()
                        .id(UUID.fromString("9b8af09b-4dee-41a6-b608-819f4978958e"))
                        .title("Фрукты")
                        .build())
                .build();

        Assertions.assertThat(response)
                .usingRecursiveComparison()
                .withStrictTypeChecking()
                .isEqualTo(expected);

    }

    @Test
    @DataSet(cleanBefore = true, cleanAfter = true, value = "datasets/api/product/delete.json")
    @ExpectedDataSet(value = "datasets/api/product/expected_delete.json")
    void delete() {

        // Arrange

       UUID id = UUID.fromString("53291b58-cc67-4697-b69e-5a824bac36e4");

        // Act

        webTestClient.delete()
                .uri(uriBuilder -> uriBuilder.path("/product/delete/"+ id)
                        .build())
                .header("Authorization", "Bearer token")
                .exchange()
                // Assert
                .expectStatus()
                .isOk();

    }
}

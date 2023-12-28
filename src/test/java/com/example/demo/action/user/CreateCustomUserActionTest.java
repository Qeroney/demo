package com.example.demo.action.user;

import com.example.demo.model.Cart;
import com.example.demo.model.CustomUser;
import com.example.demo.service.cart.CartService;
import com.example.demo.service.cart.argument.CreateCartArgument;
import com.example.demo.service.user.CustomUserService;
import com.example.demo.service.user.argument.CreateCustomUserArgument;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;

class CreateCustomUserActionTest {

    private final CustomUserService customUserService = Mockito.mock(CustomUserService.class);

    private final CartService cartService = Mockito.mock(CartService.class);

    private final CreateCustomUserAction action = new CreateCustomUserAction(customUserService, cartService);

    @Test
    void execute() {
        //Arrange
        UUID cartId = UUID.randomUUID();
        CustomUser user = Mockito.mock(CustomUser.class);

        CreateCustomUserActionArgument argument = CreateCustomUserActionArgument.builder()
                                                                                .password("test")
                                                                                .email("test")
                                                                                .build();

        Cart cart1 = Cart.builder()
                         .id(cartId)
                         .products(new ArrayList<>())
                         .build();

        Mockito.when(customUserService.create(any())).thenReturn(user);
        Mockito.when(cartService.create(any())).thenReturn(cart1);

        //Act
        CustomUser execute = action.execute(argument);

        //Assert
        ArgumentCaptor<CreateCustomUserArgument> createArgumentCaptor = ArgumentCaptor.forClass(CreateCustomUserArgument.class);
        ArgumentCaptor<CreateCartArgument> createCartArgumentCaptor = ArgumentCaptor.forClass(CreateCartArgument.class);


        Mockito.verify(customUserService, Mockito.only()).create(createArgumentCaptor.capture());
        Mockito.verify(cartService, Mockito.only()).create(createCartArgumentCaptor.capture());

        CreateCustomUserArgument expectedArgument = CreateCustomUserArgument.builder()
                                                                            .password("test")
                                                                            .email("test")
                                                                            .cart(Cart.builder()
                                                                                      .id(cartId)
                                                                                      .products(new ArrayList<>())
                                                                                      .build())
                                                                            .build();

        CreateCartArgument expectedCartArgument = CreateCartArgument.builder().build();

        Assertions.assertEquals(execute, user);

        assertThat(createArgumentCaptor.getValue())
                .usingRecursiveComparison()
                .isEqualTo(expectedArgument);
        assertThat(createCartArgumentCaptor.getValue())
                .usingRecursiveComparison()
                .isEqualTo(expectedCartArgument);
    }
}

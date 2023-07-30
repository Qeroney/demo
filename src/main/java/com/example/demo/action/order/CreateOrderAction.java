package com.example.demo.action.order;

import com.example.demo.model.Cart;
import com.example.demo.model.CustomUser;
import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.security.AuthService;
import com.example.demo.service.cart.CartService;
import com.example.demo.service.order.OrderService;
import com.example.demo.service.order.argument.CreateOrderArgument;
import com.example.demo.service.product.ProductService;
import com.example.demo.service.user.CustomUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CreateOrderAction {

    private final AuthService authService;

    private final OrderService orderService;

    private final CustomUserService customUserService;


    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Order execute(CreateOrderActionArgument argument) {

        UUID id = authService.getAuthorizedUserId();

        CustomUser user = customUserService.getExisting(id);

        Cart cart = user.getCart();
        List<Product> products = cart.getProducts();
        Long finalPrice = 0L;
        for (Product product : products) {
            finalPrice += product.getPrice();
        }

        Long balance = user.getBalance();


        if (finalPrice > balance) {
            throw new NullPointerException("No balance");
        }
        customUserService.changeBalance(id, balance - finalPrice);

        return orderService.create(CreateOrderArgument.builder()
                                                      .finalPrice(finalPrice)
                                                      .deliveryName(argument.getDeliveryName())
                                                      .deliveryPlace(argument.getDeliveryPlace())
                                                      .cart(cart)
                                                      .build());
    }
}

package com.example.demo.service.order;

import com.example.demo.model.Order;
import com.example.demo.service.order.argument.CreateOrderArgument;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    Order create(CreateOrderArgument argument);

    void delete(UUID id);

    List<Order> list();

    Order getExisting(UUID id);


}

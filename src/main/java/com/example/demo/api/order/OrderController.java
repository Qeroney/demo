package com.example.demo.api.order;

import com.example.demo.action.order.CreateOrderAction;
import com.example.demo.api.order.dto.CreateOrderDto;
import com.example.demo.api.order.dto.OrderDto;
import com.example.demo.model.Order;
import com.example.demo.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.demo.api.order.mapper.OrderMapper.ORDER_MAPPER;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    private final CreateOrderAction action;

    @GetMapping("list")
    public List<OrderDto> list() {
        return service.list()
                      .stream()
                      .map(ORDER_MAPPER::toDto)
                      .collect(Collectors.toList());
    }

    @PostMapping("create")
    public OrderDto create(@RequestBody CreateOrderDto dto) {
        Order order = action.execute(ORDER_MAPPER.toCreate(dto));

        return ORDER_MAPPER.toDto(order);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}

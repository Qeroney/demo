package com.example.demo.api.order.mapper;

import com.example.demo.action.order.CreateOrderActionArgument;
import com.example.demo.api.order.dto.CreateOrderDto;
import com.example.demo.api.order.dto.OrderDto;
import com.example.demo.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper ORDER_MAPPER = Mappers.getMapper(OrderMapper.class);

    OrderDto toDto(Order order);

    CreateOrderActionArgument toCreate(CreateOrderDto dto);
}

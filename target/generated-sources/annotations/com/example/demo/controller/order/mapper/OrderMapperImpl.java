package com.example.demo.controller.order.mapper;

import com.example.demo.action.order.CreateOrderActionArgument;
import com.example.demo.controller.order.dto.CreateOrderDto;
import com.example.demo.controller.order.dto.OrderDto;
import com.example.demo.model.Order;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-30T00:49:07+1000",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.7 (Eclipse Adoptium)"
)
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDto toDto(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDto.OrderDtoBuilder orderDto = OrderDto.builder();

        orderDto.id( order.getId() );
        orderDto.finalPrice( order.getFinalPrice() );
        orderDto.deliveryName( order.getDeliveryName() );
        orderDto.deliveryPlace( order.getDeliveryPlace() );
        orderDto.orderTime( order.getOrderTime() );

        return orderDto.build();
    }

    @Override
    public CreateOrderActionArgument toCreate(CreateOrderDto dto) {
        if ( dto == null ) {
            return null;
        }

        CreateOrderActionArgument.CreateOrderActionArgumentBuilder createOrderActionArgument = CreateOrderActionArgument.builder();

        createOrderActionArgument.deliveryName( dto.getDeliveryName() );
        createOrderActionArgument.deliveryPlace( dto.getDeliveryPlace() );

        return createOrderActionArgument.build();
    }
}

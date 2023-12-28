package com.example.demo.api.order.mapper;

import com.example.demo.action.order.CreateOrderActionArgument;
import com.example.demo.api.order.dto.CreateOrderDto;
import com.example.demo.api.order.dto.OrderDto;
import com.example.demo.model.Order;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-28T19:38:11+1000",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.9 (Eclipse Adoptium)"
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

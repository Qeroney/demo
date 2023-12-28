package com.example.demo.service.order;

import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.service.order.argument.CreateOrderArgument;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public Order create(@NonNull CreateOrderArgument argument) {
        return repository.save(Order.builder()
                                    .deliveryName(argument.getDeliveryName())
                                    .deliveryPlace(argument.getDeliveryPlace())
                                    .finalPrice(argument.getFinalPrice())
                                    .build());
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void delete(@NonNull UUID id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> list() {
        return repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Order getExisting(@NonNull UUID id) {
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }
}

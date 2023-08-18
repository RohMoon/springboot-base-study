package com.study.base.boot.aggregations.v1.order.application;

import com.study.base.boot.aggregations.v1.order.application.dto.req.CreateOrder;
import com.study.base.boot.aggregations.v1.order.domain.OrderAggregate;
import com.study.base.boot.aggregations.v1.order.infrastructure.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public void create(CreateOrder createOrder) {
        final var orderAggregate = OrderAggregate
                .builder()
                .build()
                .patch(createOrder)
                .create(orderRepository);
    }

    @Transactional
    public List<Long> createOrders(List<CreateOrder> createOrderList) {
        List<OrderAggregate> orders = createOrderList
                .stream()
                .map(createOrder -> OrderAggregate
                        .builder()
                        .build()
                        .patch(createOrder))
                .toList();

        List<OrderAggregate> savedOrders = orderRepository.saveAll(orders);

        return savedOrders
                .stream()
                .map(OrderAggregate::getId)
                .collect(Collectors.toList());
    }
}

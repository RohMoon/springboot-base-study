package com.study.base.boot.aggregations.v1.order.application;

import com.study.base.boot.aggregations.v1.order.application.dto.req.CreateOrder;
import com.study.base.boot.aggregations.v1.order.domain.entity.OrderAggregate;
import com.study.base.boot.aggregations.v1.order.domain.enumerations.OrderStatusEnum;
import com.study.base.boot.aggregations.v1.order.infrastructure.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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
    public List<Long> creates(List<CreateOrder> createOrders) {
        final var orders = OrderAggregate.creates(orderRepository, createOrders);

        return orders
                .stream()
                .map(OrderAggregate::getId)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public OrderAggregate get(long id) {
        Optional<OrderAggregate> byId = orderRepository.findById(id);
        OrderAggregate orderAggregate = byId.orElseGet(null);

        return orderAggregate;
    }

    @Transactional(readOnly = true)
    public Page<OrderAggregate> listByStatus(OrderStatusEnum orderStatus, Pageable pageable) {
        Page<OrderAggregate> allByStatus = orderRepository.findAllByStatus(orderStatus, pageable);

        return allByStatus;
    }


}

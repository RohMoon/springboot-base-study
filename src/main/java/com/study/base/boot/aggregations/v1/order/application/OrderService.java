package com.study.base.boot.aggregations.v1.order.application;

import com.study.base.boot.aggregations.v1.order.application.dto.req.CreateOrder;
import com.study.base.boot.aggregations.v1.order.domain.OrderAggregate;
import com.study.base.boot.aggregations.v1.order.infrastructure.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public void create(CreateOrder createOrder) {
        final var orderAggregate = OrderAggregate.builder()
                .build()
                .patch(createOrder)
                .create(orderRepository);

        log.info("id : {}", orderAggregate.getId());
    }
}

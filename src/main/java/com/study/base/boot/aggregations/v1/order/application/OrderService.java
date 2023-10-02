package com.study.base.boot.aggregations.v1.order.application;

import com.study.base.boot.aggregations.v1.order.application.dto.GetOrder;
import com.study.base.boot.aggregations.v1.order.application.dto.req.CreateOrder;
import com.study.base.boot.aggregations.v1.order.domain.entity.OrderAggregate;
import com.study.base.boot.aggregations.v1.order.domain.enumerations.OrderStatusEnum;
import com.study.base.boot.aggregations.v1.order.infrastructure.repository.OrderRepository;
import com.study.base.boot.aggregations.v1.order.infrastructure.repository.dto.req.OrderCondition;
import com.study.base.boot.aggregations.v1.order.infrastructure.repository.dto.res.OrderInfoProjection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public void create(CreateOrder createOrder) {
        final var orderAggregate = OrderAggregate.builder().build().patch(createOrder).create(orderRepository);
    }

    @Transactional
    public List<Long> creates(List<CreateOrder> createOrders) {
        final var orders = OrderAggregate.creates(orderRepository, createOrders);

        return orders.stream().map(OrderAggregate::getId).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<OrderAggregate> list(GetOrder request) {
        final OrderCondition condition = request.toCondition();
        final Page<OrderAggregate> pageOrders = orderRepository.getOrders(condition);
        final List<OrderInfoProjection> orders = orderRepository.getOrders(
                condition.getStartDate(),
                condition.getEndDate(),
                condition.getPrice()
        );

        log.info("orders :: { }", orders);
        return pageOrders;
    }

    @Transactional(readOnly = true)
    public OrderAggregate get(long id) {
        Optional<OrderAggregate> byId = orderRepository.findById(id);
        OrderAggregate orderAggregate = byId.orElseGet(null);

        return orderAggregate;
    }

    @Transactional(readOnly = true)
    public Page<OrderAggregate> listByStatus(OrderStatusEnum orderStatus,
                                             Pageable pageable) {
        Page<OrderAggregate> allByStatus = orderRepository.findAllByStatus(orderStatus, pageable);

        return allByStatus;
    }

    public Page<OrderAggregate> findAllByCreatedDateBetweenAndPriceBetween(LocalDateTime periodFrom,
                                                                           LocalDateTime periodTo,
                                                                           int minPrice,
                                                                           int maxPrice,
                                                                           Pageable pageable) {
        Page<OrderAggregate> allByConditions = orderRepository.findAllByCreatedDateBetweenAndPriceBetween(periodFrom,
                                                                                                          periodTo,
                                                                                                          minPrice,
                                                                                                          maxPrice,
                                                                                                          pageable);
        return allByConditions;
    }

    @Transactional
    public void changeStatus(long id,
                             OrderStatusEnum status) {
     /*   switch (status){
            case ORDER -> this.changeToOrder(id);
            case CANCELED -> this.changeToCanceled(id);
            default -> throw new IllegalArgumentException("잘못된 상태");
        }*/
        orderRepository.changeStatus(id, status);
    }

    @Transactional
    public void changeToCanceled(long id) {
        final var orderOptional = orderRepository.findById(id);

        orderOptional.ifPresent(OrderAggregate::changeCanceled);
    }

    @Transactional
    public void changeToOrder(long id) {
        final var orderOptional = orderRepository.findById(id);

        orderOptional.ifPresent(OrderAggregate::changeOrder);
    }


}

package com.study.base.boot.aggregations.v1.order.presentation;

import com.study.base.boot.aggregations.v1.order.application.OrderService;
import com.study.base.boot.aggregations.v1.order.domain.entity.order.OrderAggregate;
import com.study.base.boot.aggregations.v1.order.presentation.dto.req.CreateOrdersDto;
import com.study.base.boot.aggregations.v1.order.presentation.dto.res.OrderDto;
import com.study.base.boot.config.annotations.Get;
import com.study.base.boot.config.annotations.Post;
import com.study.base.boot.config.annotations.RestApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Slf4j
@RestApi("/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Get("/{id}")
    public OrderDto getOrder(@PathVariable long id) {
        OrderAggregate orderAggregate = orderService.get(id);

        return OrderDto
                .builder()
                .id(orderAggregate.getId())
                .orderNumber(orderAggregate.getOrderNumber())
                .orderName(orderAggregate.getOrderName())
                .status(orderAggregate.getStatus())
                .price(orderAggregate.getPrice())
                .deliveryFee(orderAggregate.getDeliveryFee())
                .address(orderAggregate.getAddress())
                .userId(orderAggregate.getUserId())
                .createdDate(orderAggregate.getCreatedDate())
                .updatedDate(orderAggregate.getUpdatedDate())
                .build();

    }

    @Post
    public List<Long> createOrders(@RequestBody @Valid CreateOrdersDto request) {
        return orderService.createOrders(request.toCreateList());
    }
}
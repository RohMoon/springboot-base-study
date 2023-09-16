package com.study.base.boot.aggregations.v1.order.presentation;

import com.study.base.boot.aggregations.v1.order.application.OrderService;
import com.study.base.boot.aggregations.v1.order.domain.entity.order.OrderAggregate;
import com.study.base.boot.aggregations.v1.order.domain.enumerations.OrderStatusEnum;
import com.study.base.boot.aggregations.v1.order.presentation.dto.req.CreateOrdersDto;
import com.study.base.boot.aggregations.v1.order.presentation.dto.res.OrderDto;
import com.study.base.boot.aggregations.v1.order.presentation.mapper.OrderEDMapper;
import com.study.base.boot.config.annotations.Get;
import com.study.base.boot.config.annotations.Post;
import com.study.base.boot.config.annotations.RestApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestApi("/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderEDMapper orderEDMapper;

    @Get("/status/{status}")
    public Page<OrderDto> listByStatus(@PathVariable OrderStatusEnum status,
                                       @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<OrderAggregate> pageOrders = orderService.listByStatus(status, pageable);
        List<OrderAggregate> orders = pageOrders.getContent();

        List<OrderDto> ordeDtos = orders
                .stream()
                .map(orderEDMapper::toDto)
                .collect(Collectors.toList());

        return new PageImpl<>(ordeDtos, pageable, pageOrders.getTotalElements());

    }

    @Get("/{id}")
    public OrderDto getOrder(@PathVariable long id) {
        OrderAggregate orderAggregate = orderService.get(id);

//        List<OrderItemEntity> items = orderAggregate.getItems();

        /*final List<OrderItemDto> itemsDtos = items
                .stream()
                .map(
                        item -> OrderItemDto
                                .builder()
                                .id(item.getId())
                                .itemId(item.getItemId())
                                .itemName(item.getItemName())
                                .status(item.getStatus())
                                .price(item.getPrice())
                                .qty(item.getQty())
                                .createdDate(item.getCreatedDate())
                                .updatedDate(item.getUpdatedDate())
                                .build()
                )
                .collect(Collectors.toList());*/

        /*return OrderDto
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
                .item(itemsDtos)
                .build();
*/
        OrderDto orderDto = orderEDMapper.toDto(orderAggregate);
        return orderDto;
    }

    @Post
    public List<Long> createOrders(@RequestBody @Valid CreateOrdersDto request) {
        return orderService.createOrders(request.toCreateList());
    }
}
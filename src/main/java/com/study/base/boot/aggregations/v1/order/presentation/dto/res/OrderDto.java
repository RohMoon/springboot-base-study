package com.study.base.boot.aggregations.v1.order.presentation.dto.res;

import com.study.base.boot.aggregations.v1.order.domain.enumerations.OrderStatusEnum;
import com.study.base.boot.config.mapstruct.base.BaseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@SuperBuilder
public class OrderDto extends BaseDto {
    private Long id;
    private String orderNumber;
    private String orderName;

    private OrderStatusEnum status;
    private int price;
    private int deliveryFee;
    private String address;
    private long userId;
    private List<OrderItemDto> items;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
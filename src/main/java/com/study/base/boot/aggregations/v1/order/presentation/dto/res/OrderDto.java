package com.study.base.boot.aggregations.v1.order.presentation.dto.res;

import com.study.base.boot.aggregations.v1.order.domain.enumerations.OrderStatusEnum;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class OrderDto {
    private Long id;
    private String orderNumber;
    private String orderName;

    private OrderStatusEnum status;
    private int price;
    private int deliveryFee;
    private String address;
    private long userId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}

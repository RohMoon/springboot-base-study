package com.study.base.boot.aggregations.v1.order.presentation.dto.req;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateOrderItemDto {

    private long itemId;
    private String itemName;
    private int price;
}

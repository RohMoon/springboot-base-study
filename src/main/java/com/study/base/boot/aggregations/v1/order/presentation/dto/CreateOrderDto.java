package com.study.base.boot.aggregations.v1.order.presentation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateOrderDto {
    private int price;
    private int deliveryFee;
    private int isTaxation;
    private String address;
}

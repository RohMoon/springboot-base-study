package com.study.base.boot.aggregations.v1.order.application.dto.req;

import lombok.Builder;

@Builder
public class CreateOrder {
    private String orderNumber;
    private String orderName;
    private String status;
    private int price;
    private int deliveryFee;
    private String address;
    private long userId;
}


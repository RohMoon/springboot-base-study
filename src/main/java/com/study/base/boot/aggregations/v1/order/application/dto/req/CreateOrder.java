package com.study.base.boot.aggregations.v1.order.application.dto.req;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateOrder {
    @NotNull
    private String orderNumber;
    @NotNull
    private String orderName;
    private String status;
    @PositiveOrZero
    private int price;
    @PositiveOrZero
    private int deliveryFee;
    @NotNull
    private String address;
    @PositiveOrZero
    private long userId;
}


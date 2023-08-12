package com.study.base.boot.aggregations.v1.order.presentation.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateOrderDto {
    @NotNull
    private String orderNumber;

    @NotNull
    private String orderName;
    private int price;
    private int deliveryFee;

    @NotNull
    private String address;
    private long userId;

//    @NotNull
//    @Valid
//    private List<CreateOrderItemDto> items;
}

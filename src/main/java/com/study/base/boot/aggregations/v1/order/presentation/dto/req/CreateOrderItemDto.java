package com.study.base.boot.aggregations.v1.order.presentation.dto.req;


import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CreateOrderItemDto {

    private long itemId;
    @NotNull
    private String itemName;
    private int price;
}

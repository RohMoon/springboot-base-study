package com.study.base.boot.aggregations.v1.order.application.dto.req;

import lombok.*;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateOrderItem {
    private long itemId;
    private String itemName;
    private int price;
    private int qty;
}

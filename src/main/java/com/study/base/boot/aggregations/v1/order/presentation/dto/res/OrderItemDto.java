package com.study.base.boot.aggregations.v1.order.presentation.dto.res;

import com.study.base.boot.aggregations.v1.order.domain.enumerations.OrderItemStatusEnum;
import com.study.base.boot.config.mapstruct.base.BaseDto;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
public class OrderItemDto extends BaseDto {
    private Long id;
    private long itemId;
    private String itemName;
    private OrderItemStatusEnum status;
    private int price;
    private int qty;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}

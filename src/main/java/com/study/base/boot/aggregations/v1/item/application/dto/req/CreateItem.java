package com.study.base.boot.aggregations.v1.item.application.dto.req;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateItem {
    @NotNull
    private String itemName;
    @PositiveOrZero
    private int price;
    private CreateItemStock stock;
}


package com.study.base.boot.aggregations.v1.item.presentation.dto.req;


import com.study.base.boot.aggregations.v1.item.application.dto.req.RegisterStockItem;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterItemStockDto {
    @PositiveOrZero
    private long itemId;
    @PositiveOrZero
    private int stockQty;

    public RegisterStockItem toCreate() {
        return RegisterStockItem
                .builder()
                .itemId(this.itemId)
                .qty(this.stockQty)
                .build();
    }
}

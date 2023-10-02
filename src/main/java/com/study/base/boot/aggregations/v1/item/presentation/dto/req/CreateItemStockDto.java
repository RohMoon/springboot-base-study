package com.study.base.boot.aggregations.v1.item.presentation.dto.req;


import com.study.base.boot.aggregations.v1.item.application.dto.req.CreateItemStock;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateItemStockDto {
    @PositiveOrZero
    private int stockQty;

    public CreateItemStock toCreate() {
        return CreateItemStock
                .builder()
                .stockQty(this.stockQty)
                .build();
    }
}

package com.study.base.boot.aggregations.v1.item.presentation.dto.req;

import com.study.base.boot.aggregations.v1.item.application.dto.req.CreateItem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;

@Getter
@SuperBuilder
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateItemDto {
    @NotNull
    private String itemName;
    @PositiveOrZero
    private int price;

    @NotNull
    @Valid
    private CreateItemStockDto stock;

    public CreateItem toCreate() {
        return CreateItem
                .builder()
                .itemName(this.itemName)
                .price(this.price)
                .stock(this.stock.toCreate())
                .build();
    }
}

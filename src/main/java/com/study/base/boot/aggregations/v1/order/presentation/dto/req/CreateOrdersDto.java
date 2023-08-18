package com.study.base.boot.aggregations.v1.order.presentation.dto.req;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Builder(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateOrdersDto {

    @Valid
    @NotNull
    @Size(min = 1)
    private List<CreateOrderDto> createOrders;
}
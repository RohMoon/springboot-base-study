package com.study.base.boot.aggregations.v1.order.presentation.dto.req;

import com.study.base.boot.aggregations.v1.order.application.dto.req.CreateOrder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateOrdersDto {

    @Valid
    @NotNull
    @Size(min = 1)
    private List<CreateOrderDto> createOrders;

    public List<CreateOrder> toCreateList() {
        return this.createOrders
                .stream()
                .map(CreateOrderDto::toCreate)
                .collect(Collectors.toList());
    }
}
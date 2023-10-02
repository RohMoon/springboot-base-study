package com.study.base.boot.aggregations.v1.order.presentation.dto.req;

import com.study.base.boot.aggregations.v1.order.application.dto.GetOrder;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Builder
public class GetOrderDto {

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @PositiveOrZero
    private int price;

    private Pageable pageable;

    public GetOrder toGetOrder(Pageable pageable) {
        return GetOrder.builder()
                       .startDate(LocalDateTime.of(this.startDate, LocalTime.MIN))
                       .endDate(LocalDateTime.of(this.endDate.plusDays(1), LocalTime.MIN))
                       .price(this.price)
                       .pageable(this.pageable)
                       .build();
    }
}

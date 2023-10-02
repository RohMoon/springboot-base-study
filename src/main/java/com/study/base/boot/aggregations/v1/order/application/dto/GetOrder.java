package com.study.base.boot.aggregations.v1.order.application.dto;

import com.study.base.boot.aggregations.v1.order.infrastructure.repository.dto.req.OrderCondition;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

@Getter
@Builder
public class GetOrder {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int price;
    private Pageable pageable;

    public OrderCondition toCondition() {
        return OrderCondition.builder()
                             .startDate(this.startDate)
                             .endDate(this.endDate)
                             .price(this.price)
                             .pageable(this.pageable)
                             .build();
    }
}

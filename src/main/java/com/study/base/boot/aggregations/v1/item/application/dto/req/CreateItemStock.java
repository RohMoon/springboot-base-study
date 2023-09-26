package com.study.base.boot.aggregations.v1.item.application.dto.req;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateItemStock {
    private int stockQty;
}

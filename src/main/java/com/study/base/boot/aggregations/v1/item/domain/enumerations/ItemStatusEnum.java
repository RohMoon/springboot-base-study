package com.study.base.boot.aggregations.v1.item.domain.enumerations;

import lombok.Getter;

@Getter
public enum ItemStatusEnum {

    SELL("판매중"),
    STOP("판매중단");

    private String statusName;

    ItemStatusEnum(String statusName) {
        this.statusName = statusName;
    }
}

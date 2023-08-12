package com.study.base.boot.aggregations.v1.order.domain;

import jakarta.persistence.*;

@Entity
@Table(catalog = "base", name = "order")
public class OrderAggregate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK값을 어떻게 생성할거니 ai
    private Long id;
}

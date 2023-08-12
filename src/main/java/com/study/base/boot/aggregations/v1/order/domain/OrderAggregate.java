package com.study.base.boot.aggregations.v1.order.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(catalog = "base", name = "order")
public class OrderAggregate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK값을 어떻게 생성할거니 ai
    private Long id;

    private String orderNumber;
    private String orderName;
    private String status;
    private int price;
    private int deliveryFee;
    private String address;

    private long userId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}

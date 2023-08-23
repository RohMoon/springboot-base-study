package com.study.base.boot.aggregations.v1.order.domain;

import com.study.base.boot.aggregations.v1.order.application.dto.req.CreateOrder;
import com.study.base.boot.aggregations.v1.order.domain.enumerations.OrderStatusEnum;
import com.study.base.boot.aggregations.v1.order.infrastructure.repository.OrderRepository;
import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Getter
@DynamicInsert
@Table(catalog = "base", name = "order")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderAggregate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNumber;
    private String orderName;
    private OrderStatusEnum status;
    private int price;
    private int deliveryFee;
    private String address;
    private long userId;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime updatedDate;

    public OrderAggregate create(OrderRepository orderRepository) {
        orderRepository.save(this);

        return this;
    }

    public static List<OrderAggregate> saveAll(OrderRepository orderRepository, List<OrderAggregate> orders) {
        return orderRepository.saveAll(orders);
    }

    public OrderAggregate patch(CreateOrder createOrder) {
        this.orderNumber = StringUtils.defaultIfEmpty(createOrder.getOrderNumber(), this.orderNumber);
        this.orderName = StringUtils.defaultIfEmpty(createOrder.getOrderNumber(), this.orderName);
        this.price = createOrder.getPrice();
        this.deliveryFee = createOrder.getDeliveryFee();
        this.address = StringUtils.defaultIfEmpty(createOrder.getOrderNumber(), this.address);
        this.userId = createOrder.getUserId();
        this.createdDate = LocalDateTime.now();

        return this;
    }
}

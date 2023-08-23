package com.study.base.boot.aggregations.v1.order.domain;

import com.study.base.boot.aggregations.v1.order.application.dto.req.CreateOrder;
import com.study.base.boot.aggregations.v1.order.domain.entity.OrderItemEntity;
import com.study.base.boot.aggregations.v1.order.domain.enumerations.OrderStatusEnum;
import com.study.base.boot.aggregations.v1.order.infrastructure.repository.OrderRepository;
import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;
    private int price;
    private int deliveryFee;
    private String address;
    private long userId;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime updatedDate;
    //orderitem의 정보가 없으니 알거 없고 다른데서 order를 쓰라고하ㅡ
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<OrderItemEntity> items; //@Entity 타입을 줘야 한다.

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

//        createOrder.getItems()
//                .forEach(item -> this.addItem(null));
        createOrder
                .getItems()
                .forEach(item -> this.addItem(
                        OrderItemEntity
                                .builder()
                                .build()
                                .patch(item)
                ));

        return this;
    }

    public OrderAggregate addItem(OrderItemEntity orderItem) {
        Assert.notNull(orderItem, "orderItem is null");

        if (this.getItems() == null) {
            this.items = new ArrayList<>();
        }

        orderItem.putOrder(this);
        this.items.add(orderItem);

        return this;
    }

}

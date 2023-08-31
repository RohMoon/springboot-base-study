package com.study.base.boot.aggregations.v1.order.domain.entity.order;

import com.study.base.boot.aggregations.v1.order.application.dto.req.CreateOrder;
import com.study.base.boot.aggregations.v1.order.domain.entity.orderItem.OrderItemEntity;
import com.study.base.boot.aggregations.v1.order.infrastructure.repository.OrderRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@DynamicInsert
@SuperBuilder
@Table(catalog = "base", name = "order")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderAggregate extends AbstractOrder {

    @OneToMany(mappedBy = "order")
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

//        orderItem.putOrder(this);
        this.items.add(orderItem);

        return this;
    }
}

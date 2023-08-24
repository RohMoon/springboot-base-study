package com.study.base.boot.aggregations.v1.order.domain.entity;

import com.study.base.boot.aggregations.v1.order.application.dto.req.CreateOrderItem;
import com.study.base.boot.aggregations.v1.order.domain.OrderAggregate;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;

@Table(catalog = "base", name = "order_item")
@Entity
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@Getter
public class OrderItemEntity extends AbstractOrderItem {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId") // 이테이블에 외래키
    private OrderAggregate order;

    public OrderItemEntity putOrder(OrderAggregate order) {
        this.order = order;
        return this;
    }

    public OrderItemEntity patch(CreateOrderItem createOrderItem) {
        this.itemId = createOrderItem.getItemId();
        this.itemName = StringUtils.defaultIfEmpty(createOrderItem.getItemName(), this.itemName);
        this.price = createOrderItem.getPrice();
        this.qty = createOrderItem.getQty();

        return this;
    }
}

package com.study.base.boot.aggregations.v1.order.domain.entity.orderItem;

import com.study.base.boot.aggregations.v1.order.application.dto.req.CreateOrderItem;
import com.study.base.boot.aggregations.v1.order.domain.entity.order.OrderAggregate;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Entity
@SuperBuilder
@DynamicInsert
@Table(catalog = "base", name = "order_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItemEntity extends AbstractOrderItem {

    //    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "orderId")
    private OrderAggregate order;

//    public OrderItemEntity putOrder(OrderAggregate order) {
//        this.order = order;
//        return this;
//    }

    public OrderItemEntity patch(CreateOrderItem createOrderItem) {
        this.itemId = createOrderItem.getItemId();
        this.itemName = StringUtils.defaultIfEmpty(createOrderItem.getItemName(), this.itemName);
        this.price = createOrderItem.getPrice();
        this.qty = createOrderItem.getQty();
        return this;
    }

}

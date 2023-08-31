package com.study.base.boot.aggregations.v1.item.domain.entity.itemStock;

import com.study.base.boot.aggregations.v1.item.application.dto.req.RegisterStockItem;
import com.study.base.boot.aggregations.v1.item.domain.entity.item.ItemAggregate;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Entity
@SuperBuilder
@DynamicInsert
@Table(catalog = "base", name = "item_stock")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemEntityStock extends AbstractItemStock {

    @OneToOne
    @JoinColumn(name = "itemId")
    private ItemAggregate item;

    public ItemEntityStock patch(RegisterStockItem registerStockItem) {
//        this.itemId = registerStockItem.getItemId();
        this.stockQty = registerStockItem.getQty();
        return this;
    }

    public void add(ItemEntityStock stock) {
        this.item = stock.item;
        this.stockQty = this.getStockQty() + stock.getStockQty();
    }

}

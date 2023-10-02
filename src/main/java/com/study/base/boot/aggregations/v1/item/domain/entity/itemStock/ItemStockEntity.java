package com.study.base.boot.aggregations.v1.item.domain.entity.itemStock;

import com.study.base.boot.aggregations.v1.item.application.dto.req.CreateItemStock;
import com.study.base.boot.aggregations.v1.item.domain.ItemAggregate;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Entity
@SuperBuilder
@DynamicInsert // Hibernate와 함께 사용되는 어노테이션, 엔티티를 데이터베이스에 저장할 때 null 값이 아닌 속성만을 삽입할수 있게 해준다.
@Table(catalog = "base", name = "item_stock")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemStockEntity extends AbstractItemStock {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId")
    private ItemAggregate item;

    public ItemStockEntity patch(CreateItemStock createItemStock) {
        this.stockQty = createItemStock.getStockQty();
        return this;
    }

    public ItemStockEntity putItem(ItemAggregate item) {
        this.item = item;

        return this;
    }

}

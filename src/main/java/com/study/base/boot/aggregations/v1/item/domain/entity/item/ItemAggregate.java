package com.study.base.boot.aggregations.v1.item.domain.entity.item;

import com.study.base.boot.aggregations.v1.item.application.dto.req.RegisterItem;
import com.study.base.boot.aggregations.v1.item.domain.entity.itemStock.ItemEntityStock;
import com.study.base.boot.aggregations.v1.item.infrastructure.repository.ItemRepository;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
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
import java.util.List;

@Getter
@Entity
@SuperBuilder
@DynamicInsert
@Table(catalog = "base", name = "item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemAggregate extends AbstractItem {

    @OneToOne(mappedBy = "item", cascade = CascadeType.PERSIST)
    private ItemEntityStock stock; //@Entity 타입을 줘야 한다.

    public static List<ItemAggregate> saveAll(ItemRepository itemRepository, List<ItemAggregate> orders) {
        return itemRepository.saveAll(orders);
    }

    public ItemAggregate create(ItemRepository itemRepository) {
        itemRepository.save(this);
        return this;
    }

    public ItemAggregate patch(RegisterItem registerItem) {
        this.itemName = StringUtils.defaultIfEmpty(registerItem.getItemName(), this.itemName);
        this.price = registerItem.getPrice();
        this.createdDate = LocalDateTime.now();

        this.add(ItemEntityStock
                         .builder()
                         .build()
                         .patch(registerItem.getStock()));
        return this;
    }

    public ItemAggregate add(ItemEntityStock stock) {
        Assert.notNull(stock, "itemStock is null");

        if (this.getStock() == null) {
            this.stock = stock;
        }

        this.stock.add(stock);

        return this;
    }
}

package com.study.base.boot.aggregations.v1.item.application;

import com.study.base.boot.aggregations.v1.item.application.dto.req.RegisterItem;
import com.study.base.boot.aggregations.v1.item.domain.entity.item.ItemAggregate;
import com.study.base.boot.aggregations.v1.item.infrastructure.repository.ItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void register(RegisterItem registerItem) {
        final var orderAggregate = ItemAggregate
                .builder()
                .build()
                .patch(registerItem)
                .create(itemRepository);
    }

    @Transactional
    public List<Long> registerItems(List<RegisterItem> registerItemList) {
        List<ItemAggregate> items = registerItemList
                .stream()
                .map(registerItem -> ItemAggregate
                        .builder()
                        .build()
                        .patch(registerItem))
                .toList();

        return ItemAggregate
                .saveAll(itemRepository, items)
                .stream()
                .map(ItemAggregate::getId)
                .collect(Collectors.toList());
    }
}

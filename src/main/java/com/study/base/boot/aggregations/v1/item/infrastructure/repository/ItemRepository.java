package com.study.base.boot.aggregations.v1.item.infrastructure.repository;

import com.study.base.boot.aggregations.v1.item.domain.entity.item.ItemAggregate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemAggregate, Long> {
}

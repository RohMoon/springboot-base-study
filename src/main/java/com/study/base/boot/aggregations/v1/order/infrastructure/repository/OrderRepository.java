package com.study.base.boot.aggregations.v1.order.infrastructure.repository;

import com.study.base.boot.aggregations.v1.order.domain.OrderAggregate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderAggregate, Long> {
}

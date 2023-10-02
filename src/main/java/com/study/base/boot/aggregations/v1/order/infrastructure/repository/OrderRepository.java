package com.study.base.boot.aggregations.v1.order.infrastructure.repository;

import com.study.base.boot.aggregations.v1.order.domain.entity.OrderAggregate;
import com.study.base.boot.aggregations.v1.order.domain.enumerations.OrderStatusEnum;
import com.study.base.boot.aggregations.v1.order.infrastructure.repository.dto.req.OrderCondition;
import com.study.base.boot.aggregations.v1.order.infrastructure.repository.dto.res.OrderInfoProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderAggregate, Long> {
    Page<OrderAggregate> findAllByStatus(OrderStatusEnum status,
                                         Pageable pageable);

    default Page<OrderAggregate> getOrders(OrderCondition condition) {
        return getOrders(
                condition.getStartDate(),
                condition.getEndDate(),
                condition.getPrice(),
                condition.getPageable()
        );
    }

    @Query(value = """
                select order
                from OrderAggregate order
                fetch join OrderItemEntity item on order.id = item.order.id
                where order.createdDate >= :startDate
                and order.createdDate < :endDate
                and order.price >= :price
            """)
    Page<OrderAggregate> getOrders(LocalDateTime startDate,
                                   LocalDateTime endDate,
                                   int price,
                                   Pageable pageable);

    @Query(value = """
            select new com.study.base.boot.aggregations.v1.order.infrastructure.repository.dto.res.OrderInfoProjection(
                    order.orderNumber,
                    order.status,
                    order.price,
                    order.createdDate
                )
                            from OrderAggregate order
                            where order.createdDate >= :startDate
                            and order.createdDate < :endDate
                            and order.price >= :price
                        """)
    List<OrderInfoProjection> getOrders(LocalDateTime startDate,
                                        LocalDateTime endDate,
                                        int price);


    Page<OrderAggregate> findAllByCreatedDateGreaterThanEqualAndCreateDateLessThanAndPriceGreaterThanEqual(
            LocalDateTime startDate,
            LocalDateTime endDate,
            int price,
            Pageable pageable
    );

    Page<OrderAggregate> findAllByCreatedDateBetweenAndPriceBetween(
            @Param("periodFrom") LocalDateTime periodFrom,
            @Param("periodTo") LocalDateTime periodTo,
            @Param("minPrice") int minPrice,
            @Param("maxPrice") int maxPrice,
            Pageable pageable
    );

    @Query("""
            update OrderAggregate  order
            set order.status = :status
            where order.id = :id
            """)
    @Modifying
    void changeStatus(long id,
                      OrderStatusEnum status);
}

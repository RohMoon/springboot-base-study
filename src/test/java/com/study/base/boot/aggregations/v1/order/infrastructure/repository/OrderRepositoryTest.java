package com.study.base.boot.aggregations.v1.order.infrastructure.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

/*    @Test
    void findAllByCreatedDateBetweenAndPriceBetween() {

        Page<OrderAggregate> page = orderRepository.findAllByCreatedDateBetweenAndPriceBetween(
                LocalDate.now().minusDays(7),
                LocalDate.now(),
                50,
                100000000,
                PageRequest.of(0,10)
        );
    }*/
}
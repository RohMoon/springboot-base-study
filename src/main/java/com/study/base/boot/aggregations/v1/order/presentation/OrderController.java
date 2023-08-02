package com.study.base.boot.aggregations.v1.order.presentation;

import com.study.base.boot.config.annotations.Get;
import com.study.base.boot.config.annotations.Post;
import com.study.base.boot.config.annotations.RestApi;

import java.util.List;

@RestApi("/v1/orders")
public class OrderController {

    @Get
    public List<String> getOrders() {
        return List.of("A", "B", "C");
    }

    @Post
    public List<String> createOrders(List<String> requestList) {
        return requestList;
    }
}
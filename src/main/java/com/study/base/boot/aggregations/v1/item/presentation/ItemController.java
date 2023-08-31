package com.study.base.boot.aggregations.v1.item.presentation;

import com.study.base.boot.aggregations.v1.item.application.ItemService;
import com.study.base.boot.aggregations.v1.item.presentation.dto.req.RegisterItemsDto;
import com.study.base.boot.config.annotations.Get;
import com.study.base.boot.config.annotations.Post;
import com.study.base.boot.config.annotations.RestApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Slf4j
@RestApi("/v1/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @Get
    public List<String> getItems() {
        return List.of("A", "B", "C");
    }

    @Post
    public List<Long> registerItems(@RequestBody @Valid RegisterItemsDto request) {
        return itemService.registerItems(request.toList());
    }
}
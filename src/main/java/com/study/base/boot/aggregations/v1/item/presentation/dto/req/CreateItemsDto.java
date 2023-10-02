package com.study.base.boot.aggregations.v1.item.presentation.dto.req;

import com.study.base.boot.aggregations.v1.item.application.dto.req.CreateItem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateItemsDto {

    @Valid
    @NotNull
    @Size(min = 1)
    private List<CreateItemDto> items;

    public List<CreateItem> toCreateItems() {
        return this.items
                .stream()
                .map(CreateItemDto::toCreate)
                .collect(Collectors.toList());
    }
}
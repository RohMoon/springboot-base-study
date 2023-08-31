package com.study.base.boot.aggregations.v1.item.domain.entity.item;

import com.study.base.boot.aggregations.v1.item.domain.enumerations.ItemStatusEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class AbstractItem {

    protected String itemName;
    @Enumerated(EnumType.STRING)
    protected ItemStatusEnum status;
    protected int price;
    @CreatedDate
    protected LocalDateTime createdDate;
    @LastModifiedDate
    protected LocalDateTime updatedDate;
    protected LocalDateTime deletedDate;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
